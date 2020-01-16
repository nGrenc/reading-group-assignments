package at.jit.readinggroup.mystery

import spock.lang.Specification
import spock.lang.Title;

class FileManipulationTest extends Specification {

    private String testFileName = 'secretFile.txt';
    private String testSuiteFileName = 'topSecretFile.txt';

    def 'should clean up after creating a file'() {
        setup: 'a file'
        createFile(testFileName)

        expect: 'all files to exist'
        new File(testFileName).exists()
        new File(testSuiteFileName).exists()
    }

    def 'the file should not exist if not created'() {
        given: 'no new file created'
        expect: 'just created files to exist'
        ! new File(testFileName).exists()
        new File(testSuiteFileName).exists()
    }

    private void createFile(String fileName) {
        def newFile = new File(fileName)
        newFile.createNewFile()
    }

    private void deleteFile(String fileName) {
        def newFile = new File(fileName)
        newFile.delete()
    }
}
