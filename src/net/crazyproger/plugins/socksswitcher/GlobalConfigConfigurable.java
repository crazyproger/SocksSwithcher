package net.crazyproger.plugins.socksswitcher;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import net.crazyproger.plugins.socksswitcher.ui.GlobalSettingsPanel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

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
