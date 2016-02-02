package factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by MZ on 2016-02-02.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationFactory {

    private static AuthenticationFactory INSTANCE;

    public static AuthenticationFactory getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new AuthenticationFactory();
        }
        return INSTANCE;
    }

    public Authorizator getAuthorizator(Map<String, String> headers, String apiUrl) {
        return new Authorizator(headers, apiUrl);
    }
}
