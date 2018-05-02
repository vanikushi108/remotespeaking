package com.ca.rs;

import com.ca.rs.models.ModelContainer;
import com.ca.rs.models.SeleniumDriverContainer;
import cucumber.api.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

public class Cleanup {
  private static final Logger LOG = LoggerFactory.getLogger(Cleanup.class);

  private final ModelContainer modelContainer;
  private final SeleniumDriverContainer seleniumDriverContainer;

  @Autowired
  public Cleanup(ModelContainer modelContainer,
                 SeleniumDriverContainer seleniumDriverContainer) {
    this.modelContainer = checkNotNull(modelContainer, "modelContainer must not be null");
    this.seleniumDriverContainer = checkNotNull(seleniumDriverContainer, "seleniumDriverContainer must not be null");
  }

  @After
  public void cleanup() throws Exception {
    //Download delete files
    if (modelContainer.get(ModelContainer.UNNAMED_DOWNLOAD) != null) {
      File downloadedFile = modelContainer.get(ModelContainer.UNNAMED_DOWNLOAD);
      downloadedFile.delete();
    }
    // clean down the spring context
    modelContainer.clear();

    // Clear the Selenium web driver, if it was used
    if (seleniumDriverContainer.isInstantiated()) {
      seleniumDriverContainer.clear();
    }
  }
}
