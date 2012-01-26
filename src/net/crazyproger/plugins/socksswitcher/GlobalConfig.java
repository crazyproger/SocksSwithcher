package net.crazyproger.plugins.socksswitcher;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;

/**
 * User: crazyproger
 * Date: 26.01.12
 */
@State(
        name = "SocksSwitcher",
        storages = {@Storage(id = "other", file = "$APP_CONFIG$/other.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class GlobalConfig
        implements PersistentStateComponent<Element> {

    private String proxyHost = null;
    private int proxyPort = 1080;
    private boolean socksEnabled = false;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
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
}
