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

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import net.crazyproger.plugins.socksswitcher.ui.GlobalSettingsPanel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * User: crazyproger
 * Date: 26.01.12
 */
public class GlobalConfigConfigurable implements SearchableConfigurable {

    private GlobalSettingsPanel globalSettingsPanel;
    private GlobalConfig configuration = ApplicationManager.getApplication().getComponent(GlobalConfig.class);

    @NotNull
    @Override
    public String getId() {
        return "SocksSwitcher.Configuration";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Socks switcher";
    }

    @Override
    public JComponent createComponent() {
        globalSettingsPanel = new GlobalSettingsPanel();

        return globalSettingsPanel.getRootPanel();
    }

    @Override
    public boolean isModified() {
        return globalSettingsPanel.isModified(configuration);
    }

    @Override
    public void apply() throws ConfigurationException {
        globalSettingsPanel.getData(configuration);
        configuration.updateSocks();
    }

    @Override
    public void reset() {
        globalSettingsPanel.setData(configuration);
    }

    @Override
    public void disposeUIResources() {
        globalSettingsPanel = null;
    }

    // not used

    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public Runnable enableSearch(String option) {
        return null;
    }
}
