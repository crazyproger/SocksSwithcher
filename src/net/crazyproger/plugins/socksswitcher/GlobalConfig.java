/*
 * Copyright 2012 Vladimir Rudev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.crazyproger.plugins.socksswitcher;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializer;
import net.crazyproger.plugins.socksswitcher.action.DisableSocks;
import net.crazyproger.plugins.socksswitcher.action.EnableSocks;
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
            EnableSocks.enable();
        } else {
            DisableSocks.disable();
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
