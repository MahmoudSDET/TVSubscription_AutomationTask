package utils;

import lombok.SneakyThrows;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;
import tests.web_tests.TestBase;

import java.util.Map;

import static org.taf.utils.ExtentReport.Utility.getScreenShot;

public class CustomSoftAssert extends SoftAssert {

    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
    private String assertMessage = null;
    @SneakyThrows
    @Override
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            assertMessage = a.getMessage();
            a.doAssert();
            onAssertSuccess(a);
            TestBase.test.pass("Test Passed");
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);

            TestBase.test.fail(assertMessage);
            TestBase.test.addScreenCaptureFromPath(getScreenShot());
            m_errors.put(ex, a);

        } finally {
            onAfterAssert(a);
        }
    }

    public void assertAll() {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(ae.getKey().getMessage());
            }
            throw new AssertionError(sb.toString());
        }
    }

}
