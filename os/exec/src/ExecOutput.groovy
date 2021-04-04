class ExecOutput implements Appendable {

    static Closure console

    StringBuilder output
    boolean print = true

    @Override
    Appendable append(CharSequence charSequence) throws IOException {
        if (output != null)
            output.append(charSequence)

        if (print)
            if (!charSequence.equals("\n") && charSequence.length() > 0)
                console.call(charSequence)
    }

    @Override
    Appendable append(CharSequence charSequence, int i, int i1) throws IOException {
        if (output != null)
            output.append(charSequence, i, i1)

        if (print)
            if (!charSequence.equals("\n") && charSequence.length() > 0)
                console.call(charSequence.subSequence(i, i1))
    }

    @Override
    Appendable append(char c) throws IOException {
        if (output != null)
            output.append(c)

        if (print)
            console.call(c)

    }
}