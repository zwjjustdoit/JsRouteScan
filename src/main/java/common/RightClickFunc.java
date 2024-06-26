package common;

import burp.BurpExtender;
import core.Content.HostContent;
import core.Content.RouteContent;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RightClickFunc {

    public static void DeleteHost(BurpExtender burp, JMenuItem menuItem){
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : burp.tab.reqDisplay.hosttab.getSelected()) {
                    burp.tab.reqDisplay.hosttab.remove(s);
                }
                burp.tab.reqDisplay.infotab.pathTab.leftTab.clear();
                burp.tab.reqDisplay.infotab.pathTab.leftTab.bottomTab.clear();
                burp.tab.reqDisplay.infotab.scanTab.requestTab.clear();
                burp.tab.reqDisplay.infotab.scanTab.detailsTab.clear();
            }
        });
    }

    public static void CleanHost(BurpExtender burp, JMenuItem menuItem){
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                burp.tab.reqDisplay.hosttab.clear();
                burp.tab.reqDisplay.infotab.pathTab.leftTab.clear();
                burp.tab.reqDisplay.infotab.pathTab.leftTab.bottomTab.clear();
                burp.tab.reqDisplay.infotab.scanTab.requestTab.clear();
                burp.tab.reqDisplay.infotab.scanTab.detailsTab.clear();

            }
        });
    }


    public static void DeleteRoute(BurpExtender burp, JMenuItem menuItem){
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selectedRoute = burp.tab.reqDisplay.infotab.pathTab.leftTab.getSelected();
                HostContent findHost = burp.tab.reqDisplay.hosttab.find(burp.tab.reqDisplay.hosttab.getSelected().get(0));
                for (String s : selectedRoute) {
                    findHost.removeRoute(s);
                    burp.tab.reqDisplay.infotab.pathTab.leftTab.remove(s);
                }
                burp.tab.reqDisplay.infotab.pathTab.leftTab.bottomTab.clear();

            }
        });
    }

    public static void PasteRoute(BurpExtender burp, JMenuItem menuItem){
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable transferable = clipboard.getContents(null);
                if(transferable != null){
                    try{
                        if(transferable.isDataFlavorSupported(DataFlavor.stringFlavor)){
                            String routeList = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                            String[] routeArr = routeList.split("\n");
                            for (String s : routeArr) {
                                burp.tab.reqDisplay.infotab.pathTab.leftTab.add(s);
                            }
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (UnsupportedFlavorException unsupportedFlavorException) {
                        unsupportedFlavorException.printStackTrace();
                    }
                }
            }
        });
    }

    public static void CleanRoute(BurpExtender burp, JMenuItem menuItem){
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HostContent findHost = burp.tab.reqDisplay.hosttab.find(burp.tab.reqDisplay.hosttab.getSelected().get(0));
                findHost.clearRoute();
                burp.tab.reqDisplay.infotab.pathTab.leftTab.clear();
                burp.tab.reqDisplay.infotab.pathTab.leftTab.bottomTab.clear();
            }
        });
    }


    public static void DeleteScan(BurpExtender burp, JMenuItem deleteMenuItem) {
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selectedRoute = burp.tab.reqDisplay.infotab.scanTab.requestTab.getSelected();
                HostContent findHost = burp.tab.reqDisplay.hosttab.find(burp.tab.reqDisplay.hosttab.getSelected().get(0));
                for (String s : selectedRoute) {
                    findHost.removeScan(s);
                    burp.tab.reqDisplay.infotab.scanTab.requestTab.remove(s);
                }
                burp.tab.reqDisplay.infotab.scanTab.detailsTab.clear();

            }
        });
    }

    public static void CleanScan(BurpExtender burp, JMenuItem clearMenuItem) {
        clearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HostContent findHost = burp.tab.reqDisplay.hosttab.find(burp.tab.reqDisplay.hosttab.getSelected().get(0));
                findHost.clearScan();
                burp.tab.reqDisplay.infotab.scanTab.requestTab.clear();
                burp.tab.reqDisplay.infotab.scanTab.detailsTab.clear();
            }
        });
    }

    public static void RefreshScan(BurpExtender burp, JMenuItem refreshMenuItem) {
        refreshMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                burp.tab.reqDisplay.infotab.scanTab.requestTab.updateAll();
            }
        });
    }
}
