package com.shpp.p2p.cs.ikripaka.assignment14;

/**
 * This class manages program
 * It recognises in what way it should go
 * (archiving / unpacking)
 */
class Switcher extends Constants {
    private String[] args;

    Switcher(String args[]) {
        this.args = args;
    }

    /**
     * Recognize in what way program should go
     * (archiving / unpacking)
     * It looks on the first parameter on the file format
     *
     * @throws Exception
     */
    void recognizeAction() throws Exception {
        boolean fileWillBeArchived;
        if (args.length == 0) {
            Archiver archiver = new Archiver(DEFAULT_FILE, DEFAULT_FILE + ".par");
            archiver.archive();
        } else if (args.length == 1) {
            String[] splittedLine = args[0].split("\\.");
            fileWillBeArchived = !splittedLine[splittedLine.length - 1].equals("par");

            if (fileWillBeArchived) {
                Archiver archiver = new Archiver(args[0], args[0] + ".par");
                archiver.archive();
            } else {
                StringBuilder builder = new StringBuilder(args[0]);
                builder.append(".uar");

                if (splittedLine.length > 2) {
                    builder.delete(0, builder.length());
                    for (int i = 0; i < splittedLine.length - 1; i++) {
                        builder.append(splittedLine[i]).append(".");
                    }
                }
                Unpacker unpackerFile = new Unpacker(args[0], builder.toString());
                unpackerFile.unzip();
            }

        } else if (args.length == 2) {
            String[] splittedLine = args[0].split("\\.");
            fileWillBeArchived = !splittedLine[splittedLine.length - 1].equals("par");

            if (fileWillBeArchived) {
                Archiver archiver = new Archiver(args[0], args[1]);
                archiver.archive();
            } else {
                Unpacker unpacker = new Unpacker(args[0], args[1]);
                unpacker.unzip();
            }
        } else if (args.length == 3) {
            fileWillBeArchived = (args[0].equals("-a"));

            if (fileWillBeArchived) {
                Archiver archiver = new Archiver(args[1], args[2]);
                archiver.archive();
            } else {
                Unpacker unpacker = new Unpacker(args[1], args[2]);
                unpacker.unzip();
            }
        }
    }
}
