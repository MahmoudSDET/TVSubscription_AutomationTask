## ExtentReport

See here for complete docs.

## Usage

See the Listeners section for usage instructions.

## Examples

An example project is available here to understand the usage.

## Note: when using this Report, you are not required to add any code in your project. The extent Report works for you to generate the report.

See this example of how a test is created, with add listener mark. Note: a good approach is to add @Listener on the top
of class only once.

@Listeners(utils.ExtentReport.TestListener.class)
public class SimpleAssertTests {

    @Test
    public void passTest() {
        Assert.assertTrue(true);
    }
    
    @Test
    public void failTest() {
        Assert.assertTrue(false);
    }

}
