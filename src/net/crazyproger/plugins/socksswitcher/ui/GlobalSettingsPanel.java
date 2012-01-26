package net.crazyproger.plugins.socksswitcher.ui;

import net.crazyproger.plugins.socksswitcher.GlobalConfig;

import javax.swing.*;
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

    public void setData(GlobalConfig data) {
        hostTF.setText(data.getProxyHost());
        portTF.setText(Integer.toString(data.getProxyPort()));
        enabledTB.setSelected(data.isSocksEnabled());
        updateToggleButtonLabel(data.isSocksEnabled());
    }

    public void getData(GlobalConfig data) {
        data.setProxyHost(hostTF.getText());
        try {
            data.setProxyPort(Integer.parseInt(portTF.getText()));
        } catch (NumberFormatException e) {
            //todo show error label
        }
        data.setSocksEnabled(enabledTB.isSelected());
    }

    public boolean isModified(GlobalConfig data) {
        if (hostTF.getText() != null ? !hostTF.getText().equals(data.getProxyHost()) : data.getProxyHost() != null)
            return true;
        int port = 0;
        try {
            port = Integer.parseInt(portTF.getText());
        } catch (NumberFormatException e) {
            //todo show error label
            return true;
        }
        if (portTF.getText() != null ? port != data.getProxyPort() : data.getProxyPort() != 0)
            return true;
        if (enabledTB.isSelected() != data.isSocksEnabled()) return true;
        return false;
    }

    private void updateToggleButtonLabel(boolean selected) {
        if (selected) {
            enabledTB.setText("Socks enabled");
        } else {
            enabledTB.setText("Socks disabled");
        }
    }
}
