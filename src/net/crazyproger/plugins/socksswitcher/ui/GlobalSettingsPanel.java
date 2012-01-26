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
