package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.de.deloma.filedeleter.Ordner;
import main.java.de.deloma.filedeleter.OrdnerDaoImpl;

class OrdnerDaoImplTest {

    private static OrdnerDaoImpl testerDao;

    @BeforeEach
    void setup() {
        TestFilesUtils.createConfigTest();
        testerDao = new OrdnerDaoImpl(TestFilesUtils.CONFIG_TEST);
    }

    @AfterEach
    void clear() {
        TestFilesUtils.deleteConfigTest();
        testerDao = null;
    }

    @Test
    void testGetAllOrdner() throws IOException {
        TestFilesUtils.initGetAllOrdner();

        Set<Ordner> ordSet = testerDao.getAllOrdner();
        assertEquals(3, ordSet.size());

        Ordner o1 = TestUtil.getOrdner("G:\\deloma\\deloma_1", ordSet);
        assertNotNull(o1);
        assertTrue(o1.isActive());

        Ordner o2 = TestUtil.getOrdner("G:\\deloma\\deloma_2", ordSet);
        assertNotNull(o2);
        assertFalse(o2.isActive());

        Ordner o3 = TestUtil.getOrdner("G:\\deloma\\deloma_3", ordSet);
        assertNotNull(o3);
        assertTrue(o3.isActive());
    }

    @Test
    void testCreateOrdner() {
        Set<Ordner> ordSet;

        Ordner ordner1 = new Ordner("G:\\deloma\\deloma_1");
        testerDao.createOrdner(ordner1);

        ordSet = testerDao.getAllOrdner();

        assertNotNull(TestUtil.getOrdner(ordner1.getPfad(), ordSet));
        assertEquals(1, ordSet.size());

        Ordner ordne2 = new Ordner("G:\\deloma\\deloma_2");
        testerDao.createOrdner(ordne2);

        ordSet = testerDao.getAllOrdner();

        assertNotNull(TestUtil.getOrdner(ordne2.getPfad(), ordSet));
        assertEquals(2, ordSet.size());

        Ordner ordner3 = new Ordner("G:\\deloma\\deloma_3");
        testerDao.createOrdner(ordner3);

        ordSet = testerDao.getAllOrdner();

        assertNotNull(TestUtil.getOrdner(ordner3.getPfad(), ordSet));
        assertEquals(3, ordSet.size());
    }

    @Test
    void testDeleteOrdner() throws IOException {
        TestFilesUtils.initDeleteOrdner();

        Set<Ordner> ordSet = testerDao.getAllOrdner();

        assertEquals(3, ordSet.size());

        Ordner ordner4 = new Ordner("G:\\deloma\\deloma_4");
        testerDao.deleteOrdner(ordner4);

        ordSet = testerDao.getAllOrdner();

        assertNull(TestUtil.getOrdner(ordner4.getPfad(), ordSet));
        assertEquals(2, ordSet.size());

        Ordner ordner5 = new Ordner("G:\\deloma\\deloma_5");
        testerDao.deleteOrdner(ordner5);

        ordSet = testerDao.getAllOrdner();

        assertNull(TestUtil.getOrdner(ordner5.getPfad(), ordSet));
        assertEquals(1, ordSet.size());

        Ordner ordner6 = new Ordner("G:\\deloma\\deloma_6");
        testerDao.deleteOrdner(ordner6);

        ordSet = testerDao.getAllOrdner();

        assertNull(TestUtil.getOrdner(ordner6.getPfad(), ordSet));
        assertEquals(0, ordSet.size());
    }

    @Test
    void testUpdateOrdner() throws IOException {
        TestFilesUtils.initUpdateOrdner();

        Set<Ordner> ordSet = testerDao.getAllOrdner();

        Ordner o7 = TestUtil.getOrdner("G:\\deloma\\deloma_7", ordSet);
        assertTrue(o7.isActive());

        o7.setActive(false);
        testerDao.updateOrdner(o7);

        ordSet = testerDao.getAllOrdner();

        Ordner o7new = TestUtil.getOrdner(o7.getPfad(), ordSet);
        assertFalse(o7new.isActive());

        Ordner o8 = TestUtil.getOrdner("G:\\deloma\\deloma_8", ordSet);
        assertTrue(o8.isActive());

        o8.setActive(false);
        testerDao.updateOrdner(o8);

        ordSet = testerDao.getAllOrdner();

        Ordner o8new = TestUtil.getOrdner(o8.getPfad(), ordSet);
        assertFalse(o8new.isActive());

        Ordner o9 = TestUtil.getOrdner("G:\\deloma\\deloma_9", ordSet);
        assertFalse(o9.isActive());

        o9.setActive(true);
        testerDao.updateOrdner(o9);

        ordSet = testerDao.getAllOrdner();

        Ordner o9new = TestUtil.getOrdner(o9.getPfad(), ordSet);
        assertTrue(o9new.isActive());
    }

    @Test
    void testDeleteFiles() throws IOException {
        TestFilesUtils.initDeleteFiles();

        Set<Ordner> ordSet = testerDao.getAllOrdner();

        Ordner deloma = new Ordner("H:\\test\\deloma");
        File folderDeloma = new File(deloma.getPfad());
        TestUtil.createSysFolder(folderDeloma);

        Ordner deloma10 = TestUtil.getOrdner("H:\\test\\deloma\\deloma_10", ordSet);
        File folderDeloma10 = new File(deloma10.getPfad());
        TestUtil.createSysFolder(folderDeloma10);

        File delomaText1 = new File("H:\\test\\deloma\\deloma_10\\delomaText1.txt");
        TestUtil.createSysFile(delomaText1);

        Ordner deloma11 = TestUtil.getOrdner("H:\\test\\deloma\\deloma_11", ordSet);
        File folderDeloma11 = new File(deloma11.getPfad());
        TestUtil.createSysFolder(folderDeloma11);

        Ordner deloma11_1 = new Ordner("H:\\test\\deloma\\deloma_11\\deloma11_1");
        File folderDeloma11_1 = new File(deloma11_1.getPfad());
        TestUtil.createSysFolder(folderDeloma11_1);

        File delomaText2 = new File("H:\\test\\deloma\\deloma_11\\deloma11_1\\delomaText2.txt");
        TestUtil.createSysFile(delomaText2);

        File delomaText3 = new File("H:\\test\\deloma\\deloma_11\\delomaText3.txt");
        TestUtil.createSysFile(delomaText3);

        Ordner deloma12 = TestUtil.getOrdner("H:\\test\\deloma\\deloma_12", ordSet);
        File fileDeloma12 = new File(deloma12.getPfad());
        TestUtil.createSysFolder(fileDeloma12);

        File delomaText4 = new File("H:\\test\\deloma\\deloma_12\\delomaText4.txt");
        TestUtil.createSysFile(delomaText4);

        testerDao.deleteFiles();

        assertFalse(delomaText1.exists());
        assertTrue(folderDeloma10.exists());

        assertFalse(folderDeloma11_1.exists());
        assertFalse(delomaText3.exists());
        assertTrue(folderDeloma11.exists());

        assertTrue(delomaText4.exists());
        assertTrue(folderDeloma10.exists());
        assertTrue(folderDeloma.exists());

        TestUtil.deleteSysFile(folderDeloma);
    }
    



}