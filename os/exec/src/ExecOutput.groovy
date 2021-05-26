class ExecOutput implements Appendable {

    static $Logger logger

    StringBuilder output
    boolean print = true

    @Override
    Appendable append(CharSequence charSequence) throws IOException {
        if (output != null)
            output.append(charSequence)

        if (print)
            if (!charSequence.equals("\n") && charSequence.length() > 0)
                logger.info("<<< " + charSequence.toString())
    }

    @Override
    Appendable append(CharSequence charSequence, int i, int i1) throws IOException {
        throw new IllegalStateException("Not implemented")
    }

    @Override
    Appendable append(char c) throws IOException {
        throw new IllegalStateException("Not implemented")

    }
}