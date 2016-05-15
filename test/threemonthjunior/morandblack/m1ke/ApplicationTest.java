package threemonthjunior.morandblack.m1ke;

// import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApplicationTest {
    
    public static Path workingPath;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        workingPath = Paths.get(System.getProperty("user.dir"));
        workingPath = workingPath.resolve("temp/");
        
        rmDirRecursively(workingPath);
        Files.createDirectories(workingPath);
        
        Path testFile1Path = workingPath.resolve(Paths.get("test.txt")); 
        Path testFile2Path = workingPath.resolve(Paths.get("dir1/dir2"));
        Files.createDirectories(testFile2Path);
        testFile2Path = testFile2Path.resolve(Paths.get("test2.txt"));
        
        try (Writer output = Files.newBufferedWriter(testFile1Path)) {
            output.write("Test file 1 w/o changes!");
        }
        
        try (Writer output = Files.newBufferedWriter(testFile2Path)) {
            output.write("Test file 2 w/o changes!");
        }
        
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        rmDirRecursively(workingPath);
    }

    @Test
    public void testCasualCase() {
        Application.main("init");
        Application.main("integrate", "temp/");
        Application.main("save", "-m", "first commit");
        Application.main("create-branch", "dev");
        Application.main("save", "-m", "second commit");
        Application.main("create-branch", "new-branch");
        Application.main("get-branch", "new-branch");
        Application.main("save");
        Application.main("get-branch", "master");
        Application.main("remove-branch", "master");
        Application.main("remove-branch", "dev");
        Application.main("get-branch", "new-branch");
        Application.main("quit");
    }
    
    
    /**
     * A helper method to delete a directory recursively.
     * 
     * Copypasted from <a href = "http://www.adam-bien.com/roller/abien/entry/java_7_deleting_recursively_a">here</a>
     * 
     * @param directory
     * @throws IOException
     */
    private static void rmDirRecursively(Path directory) throws IOException {
        if (Files.exists(directory)) {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }

            });
        }
    }

}
