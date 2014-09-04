package com.ww.rentokil.processor.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestFileStreamProcessor.class, TestSftpFileProcessor.class })
public class RentokilDevTestSuite {

}
