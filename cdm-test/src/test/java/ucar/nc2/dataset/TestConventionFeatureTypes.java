/* Copyright */
package ucar.nc2.dataset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ucar.nc2.constants.FeatureType;
import ucar.nc2.ft.FeatureDataset;
import ucar.nc2.ft.FeatureDatasetFactoryManager;
import ucar.nc2.ft2.coverage.adapter.DtCoverageCSBuilder;
import ucar.unidata.util.test.category.NeedsCdmUnitTest;
import ucar.unidata.util.test.TestDir;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Category(NeedsCdmUnitTest.class)
@RunWith(Parameterized.class)
public class TestConventionFeatureTypes {
  private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  static String base = TestDir.cdmUnitTestDir + "conventions/";

  @Parameterized.Parameters(name = "{0}")
  public static List<Object[]> getTestParameters() {
    List<Object[]> result = new ArrayList<>();

    result.add(new Object[]{"atd", FeatureType.GRID});
    result.add(new Object[]{"atd-radar", FeatureType.GRID});
    result.add(new Object[]{"avhrr", FeatureType.GRID});
    result.add(new Object[]{"awips", FeatureType.GRID});
    result.add(new Object[]{"cedric", FeatureType.GRID});
    result.add(new Object[]{"cf", FeatureType.GRID});
    result.add(new Object[]{"cf/dsc", FeatureType.POINT});
    result.add(new Object[]{"cfradial", FeatureType.RADIAL});
    result.add(new Object[]{"coards", FeatureType.GRID});
    result.add(new Object[]{"csm", FeatureType.GRID});
    result.add(new Object[]{"gdv", FeatureType.GRID});
    result.add(new Object[]{"gief", FeatureType.GRID});
    result.add(new Object[]{"ifps", FeatureType.GRID});
    result.add(new Object[]{"m3io", FeatureType.GRID});
    result.add(new Object[]{"mars", FeatureType.GRID});
    //result.add(new Object[]{"mm5", FeatureType.GRID});   // Dataset lacks X and Y axes.
    result.add(new Object[]{"nuwg", FeatureType.GRID});
    result.add(new Object[]{"wrf", FeatureType.GRID});
    result.add(new Object[]{"zebra", FeatureType.GRID});

    return result;
  }

  FeatureType type;
  File dir;

  public TestConventionFeatureTypes(String dir, FeatureType type) {
    this.type = type;
    this.dir = new File(base + dir);
  }

  @Test
  public void testFeatureDatasets() throws IOException {
    for (File f :  TestDir.getAllFilesInDirectoryStandardFilter(dir)) {
      logger.debug("Open FeatureDataset {}", f.getPath());
      try (FeatureDataset fd = FeatureDatasetFactoryManager.open(type, f.getPath(), null, new Formatter())) {
        Assert.assertNotNull(f.getPath(), fd);
        if (type == FeatureType.GRID)
          Assert.assertTrue(f.getPath(), fd.getFeatureType().isCoverageFeatureType());
        else if (type == FeatureType.POINT)
          Assert.assertTrue(f.getPath(), fd.getFeatureType().isPointFeatureType());
      }
    }
  }

  @Test
   public void testCoverageDatasets() throws IOException {
    if (type != FeatureType.GRID) return;
     for (File f :  TestDir.getAllFilesInDirectoryStandardFilter(dir)) {
       logger.debug("Open CoverageDataset {}", f.getPath());
       try (NetcdfDataset ds = NetcdfDataset.openDataset(f.getPath())) {
         DtCoverageCSBuilder builder = DtCoverageCSBuilder.classify(ds, new Formatter());
         Assert.assertNotNull(builder);
       }
     }
   }
 }
