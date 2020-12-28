import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory

import org.apache.commons.text.StringEscapeUtils

class MustacheHelper {
    private final MustacheFactory mf = new DefaultMustacheFactory()

    private final Closure console

    MustacheHelper(Closure console) {
        this.console = console
    }

    Map read(File template) {
        def output = [
                placeholders: new HashSet<String>()
        ]

        Mustache mustache = mf.compile(template.newReader(), template.absolutePath)

        mustache.codes.each {
            if (!it.name)
                return

            output.placeholders << it.name
        }

        return output
    }

    List<String> missing(File template) {
        assert template, 'Template is required'
        assert template.exists(), 'Template must exist on current filesystem'
        assert template.isFile(), 'Template must be a File (not a directory, or any other type)'

        Mustache mustache = mf.compile(template.newReader(), template.absolutePath)

        return mustache.codes
                .findAll { it instanceof com.github.mustachejava.codes.ValueCode }
                .collect { it.name }
    }

    void parse(File template, Object data, File output) {
        assert template, 'Template is required'
        assert template.exists(), 'Template must exist on current filesystem'
        assert template.isFile(), 'Template must be a File (not a directory, or any other type)'

        assert output, 'Output is required'

        // Will not proceed without data
        if (!data) {
            console "[TEMPLATE] template: ${template.absolutePath}, message: 'null data'"
            return
        }

        Mustache mustache = mf.compile(template.newReader(), template.absolutePath)

        StringWriter writer = new StringWriter()
        mustache.execute(writer, data)

        // Delete existing content, remove escaping characters and write the content
        output.delete()
        output << StringEscapeUtils.unescapeHtml4(writer.toString())
        writer.flush()
    }

    String parseText(File template, Object data) {
        assert template, 'Template is required'
        assert template.exists(), 'Template must exist on current filesystem'
        assert template.isFile(), 'Template must be a File (not a directory, or any other type)'

        assert output, 'Output is required'

        // Will not proceed without data
        if (!data) {
            console "[TEMPLATE] template: ${template.absolutePath}, message: 'null data'"
            return
        }

        // Check if templatable
        if (!parsable(template)) {
            console "[TEMPLATE] template: ${template.absolutePath}, message: 'not templatable'"
            return
        }

        Mustache mustache = mf.compile(template.newReader(), template.absolutePath)

        StringWriter writer = new StringWriter()
        mustache.execute(writer, data)

        return StringEscapeUtils.unescapeHtml4(writer.toString())
    }
}