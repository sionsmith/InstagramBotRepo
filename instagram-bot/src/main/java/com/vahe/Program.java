package com.vahe;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Program {

    private static final Logger logger = LoggerFactory.getLogger(Program.class);
	private static  InsForm INS_FORM;

    /**
	 * GUI main
	 * 
	 * @param args
	 */
	public static void main(String... args) throws Exception {

        //command line options...
        //instagram username*
        //password*
        //tags*
        //like delay (5)
        //like per photo (10
        //max likes per hour (330)
//        OptionSet options = null;
//        OptionParser parser = new OptionParser();
//        OptionSpec<Integer> port = parser.accepts("port", "Port to bind the server on")
//                .withRequiredArg().ofType(Integer.class).required();
//        parser.accepts("help").forHelp();
//        try {
//            options = parser.parse(args);
//        } catch(OptionException e) {
//            System.out.println("Error with command line arguments: " + e.getMessage());
//            parser.printHelpOn(System.out);
//            System.exit(2);
//        }
//
//        int serverPort = options.valueOf(port);
//        logger.info("starting jetty server on port: " + serverPort);
//

        try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			logger.info("Exception in main 1 {}", e);
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				logger.info("Exception in main 2 {}", e);
			}
		}

		INS_FORM = new InsForm();
		INS_FORM.setVisible(true);
	}

}
