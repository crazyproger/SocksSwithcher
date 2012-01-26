package net.crazyproger.plugins.socksswitcher.ui;

import net.crazyproger.plugins.socksswitcher.GlobalConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: crazyproger
 * Date: 26.01.12
 */
public class GlobalSettingsPanel {
    private JPanel rootPanel;
    private JTextField hostTF;
    private JTextField portTF;
    private JToggleButton enabledTB;

    public GlobalSettingsPanel() {
        enabledTB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateToggleButtonLabel(enabledTB.isSelected());
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void updateToggleButtonLabel(boolean selected) {
        if (selected) {
            enabledTB.setText("Socks enabled");
        } else {
            enabledTB.setText("Socks disabled");
        }
    }

    public void setData(GlobalConfig data) {
        hostTF.setText(data.getProxyHost());
        portTF.setText(data.getProxyPort());
        enabledTB.setSelected(data.isSocksEnabled());
        updateToggleButtonLabel(data.isSocksEnabled());
    }

    public void getData(GlobalConfig data) {
        data.setProxyHost(hostTF.getText());
        data.setProxyPort(portTF.getText());
        data.setSocksEnabled(enabledTB.isSelected());
    }

    public boolean isModified(GlobalConfig data) {
        if (hostTF.getText() != null ? !hostTF.getText().equals(data.getProxyHost()) : data.getProxyHost() != null)
            return true;
        if (portTF.getText() != null ? !portTF.getText().equals(data.getProxyPort()) : data.getProxyPort() != null)
            return true;
        if (enabledTB.isSelected() != data.isSocksEnabled()) return true;
        return false;
    }
}
