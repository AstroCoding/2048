/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: Runs all testing modules.
 */

package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestTileT.class, TestBoardT.class, TestGameController.class })

public class TestAll {
}
