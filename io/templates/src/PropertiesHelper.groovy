class PropertiesHelper {

    private final Closure console

    PropertiesHelper(Closure console) {
        this.console = console
    }

    List<String> missing(File template) {
        assert template, 'Template is required'
        assert template.exists(), 'Template must exist on current filesystem'
        assert template.isFile(), 'Template must be a File (not a directory, or any other type)'

        // Create the properties file configuration
        def props = new Properties()
        props.load(template.newReader())

        return props.keySet()
            .findAll { String key -> !props.contains(key) || !props.get(key) }
            .collect()
    }

    void parse(File template, Object data, File output) {
        assert template, 'Template is required'
        assert template.exists(), 'Template must exist on current filesystem'
        assert template.isFile(), 'Template must be a File (not a directory, or any other type)'

        assert output, 'Output is required'

        def props = new Properties()
        props.load(template.newReader())

        // Do the update
        if (data instanceof Map) {
            def mapData = data as Map<String, Object>

            for (String key : props.keySet()) {
                if (!mapData.containsKey(key)) {
                    continue
                }

                props.put(key, mapData.get(key).toString())
            }
        }

        // Save file (replace)
        props.save(new FileOutputStream(output), null)
    }
}