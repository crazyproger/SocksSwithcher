package net.crazyproger.plugins.socksswitcher;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

/**
 * User: crazyproger
 * Date: 26.01.12
 */
@State(
        name = "SocksSwitcher",
        storages = {@Storage(id = "other", file = "$APP_CONFIG$/other.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class GlobalConfig
        implements PersistentStateComponent<Element>, ApplicationComponent {

    public static final String SOCKS_HOST = "socksProxyHost";
    public static final String SOCKS_PORT = "socksProxyPort";

    private String proxyHost = null;
    private String proxyPort = "1080";
    private boolean socksEnabled = false;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public boolean isSocksEnabled() {
        return socksEnabled;
    }

    public void setSocksEnabled(boolean socksEnabled) {
        this.socksEnabled = socksEnabled;
    }

    @Override
    public Element getState() {
        return XmlSerializer.serialize(this);
    }

    @Override
    public void loadState(final Element state) {
        XmlSerializer.deserializeInto(this, state);
    }

    public void updateSocks() {
        if (isSocksEnabled()) {
            System.setProperty(SOCKS_HOST, getProxyHost());
            System.setProperty(SOCKS_PORT, getProxyPort());
        } else {
            System.clearProperty(SOCKS_HOST);
            System.clearProperty(SOCKS_PORT);
        }
    }

    @Override
    public void initComponent() {
        updateSocks();
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "SocksSwitcher.GlobalConfig";
    }

    @Override
    public void disposeComponent() {

    }
}
