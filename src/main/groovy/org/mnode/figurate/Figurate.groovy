/**

* This file is part of Base Modules.
 *
 * Copyright (c) 2009, Ben Fortuna [fortuna@micronode.com]
 *
 * Base Modules is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Base Modules is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Base Modules.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.mnode.figurate

import static java.lang.Math.min;
import static java.lang.Math.max;
import java.awt.SystemTray
import java.awt.PopupMenu
import java.awt.MenuItem
import java.awt.event.MouseEvent;
import java.awt.TrayIcon
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.text.*;
import java.awt.Color
import java.awt.Font
import java.awt.Insets
import java.awt.Dimension
import java.awt.Point
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.Desktop
import java.awt.FlowLayout
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import javax.swing.Action
import javax.swing.DefaultComboBoxModel
import javax.swing.DefaultListModel
import javax.swing.DefaultListCellRenderer
import javax.swing.Icon
import javax.swing.JFrame
import javax.swing.JComboBox
import javax.swing.JFileChooser
import javax.swing.JTabbedPane
import javax.swing.JScrollPane
import javax.swing.JList
import javax.swing.JOptionPane
import javax.swing.DefaultComboBoxModel
import javax.swing.UIManager
import javax.swing.filechooser.FileSystemView
import javax.swing.event.HyperlinkListener
import javax.swing.event.HyperlinkEvent
import javax.swing.event.UndoableEditListener
import javax.swing.event.UndoableEditEvent
import javax.swing.event.DocumentListener
import javax.swing.event.DocumentEvent
import javax.swing.undo.UndoManager
import groovy.beans.Bindable
//import groovy.swing.SwingXBuilder
import groovy.swing.LookAndFeelHelper
import groovy.swing.SwingBuilder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel
import org.pushingpixels.substance.api.SubstanceConstants
import org.pushingpixels.substance.api.SubstanceConstants.TabCloseKind
import org.pushingpixels.substance.api.tabbed.TabCloseCallback
import org.pushingpixels.substance.api.tabbed.VetoableTabCloseListener
import org.pushingpixels.lafwidget.LafWidget;
import org.pushingpixels.lafwidget.tabbed.DefaultTabPreviewPainter
import org.jvnet.flamingo.bcb.*
import org.jvnet.flamingo.bcb.core.BreadcrumbFileSelector
import org.jvnet.flamingo.common.JCommandButton
import org.jvnet.flamingo.common.JCommandButtonStrip
import org.jvnet.flamingo.common.JCommandToggleButton
import org.jvnet.flamingo.common.CommandToggleButtonGroup
import org.jvnet.flamingo.common.CommandButtonDisplayState
import org.jvnet.flamingo.svg.SvgBatikResizableIcon
//import org.mnode.base.desktop.tracker.TrackerRegistry;
import org.fife.ui.rtextarea.RTextScrollPane
import org.fife.ui.rtextarea.Gutter
import org.fife.ui.rtextarea.RTextArea
import org.fife.ui.rtextarea.RTextAreaEditorKit
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.SyntaxConstants
import com.xduke.xswing.DataTipManager
import org.jdesktop.swingx.JXStatusBar
import org.jdesktop.swingx.JXStatusBar.Constraint
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.IncreaseFontSizeAction
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.DecreaseFontSizeAction
import org.fife.ui.rtextarea.RTextAreaEditorKit.TimeDateAction
import org.fife.ui.rtextarea.RTextAreaEditorKit.BeginRecordingMacroAction
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.pushingpixels.substance.api.tabbed.VetoableMultipleTabCloseListener
import org.jdesktop.jxlayer.JXLayer
import org.jdesktop.jxlayer.plaf.AbstractLayerUI
import java.awt.Graphics2D
//import org.mnode.base.desktop.PaddedIcon
import org.mnode.base.commons.FileComparator
//import org.mnode.base.substance.TabCloseCallbackImpl
import org.mnode.ousia.HyperlinkBrowser;
import org.mnode.ousia.OusiaBuilder;

 /**
  * @author fortuna
  *
  */
  /*
@Grapes([
    @Grab(group='org.mnode.figurate', module='figurate', version='0.0.1-SNAPSHOT'),
    @Grab(group='org.springframework', module='spring-context', version='3.0.0.RELEASE'),
    @Grab(group='org.mnode.base', module='base-commons', version='0.0.1-SNAPSHOT'),
    @Grab(group='org.mnode.base', module='base-desktop', version='0.0.1-SNAPSHOT'),
    @Grab(group='org.mnode.base', module='base-substance', version='0.0.1-SNAPSHOT'),
//    @Grab(group='org.codehaus.griffon.swingxbuilder', module='swingxbuilder', version='0.1.6'),
//    @Grab(group='net.java.dev.substance', module='substance', version='5.3'),
//    @Grab(group='net.java.dev.substance', module='substance-swingx', version='5.3'),
    //@Grab(group='org.swinglabs', module='swingx', version='0.9.2'),
//    @Grab(group='org.mnode.base', module='base-views', version='0.0.1-SNAPSHOT'),
    //@Grab(group='jgoodies', module='forms', version='1.0.5'),
    //@Grab(group='org.codehaus.griffon.flamingobuilder', module='flamingobuilder', version='0.2'),
    @Grab(group='net.java.dev.flamingo', module='flamingo', version='4.2'),
//    @Grab(group='org.apache.xmlgraphics', module='batik-awt-util', version='1.7'),
//    @Grab(group='org.apache.xmlgraphics', module='batik-swing', version='1.7'),
//    @Grab(group='org.apache.xmlgraphics', module='batik-transcoder', version='1.7'),
    @Grab(group='net.java.dev.datatips', module='datatips', version='20091219'),
//    @Grab(group='org.swinglabs', module='jxlayer', version='3.0.4'),
    @Grab(group='com.fifesoft.rsyntaxtextarea', module='rsyntaxtextarea', version='1.4.0')])
    */
class Figurate {
     
    static void close(def frame, def exit) {
        if (exit) {
            System.exit(0)
        }
        else {
            frame.visible = false
        }
    }
    
    static void main(args) {
    
        // try to connect to existing control..
        //def context = new ClassPathXmlApplicationContext("/FigurateControlProxy.xml", Figurate.class)
        //def control = context.getBean("FigurateControl")
        
        //if (!control) {
        //    context = new ClassPathXmlApplicationContext("/FigurateControl.xml", Figurate.class)
        //    control = context.getBean("FigurateControl")
        //}
        
//         UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0))
//         UIManager.put(org.pushingpixels.lafwidget.LafWidget.ANIMATION_KIND, org.pushingpixels.lafwidget.utils.LafConstants.AnimationKind.FAST.derive(2))
         //UIManager.put(org.jvnet.lafwidget.LafWidget.TABBED_PANE_PREVIEW_PAINTER, new DefaultTabPreviewPainter())
//         LookAndFeelHelper.instance.addLookAndFeelAlias('substance6', 'org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel')
//         LookAndFeelHelper.instance.addLookAndFeelAlias('seaglass', 'com.seaglasslookandfeel.SeaGlassLookAndFeel')
         
//         def swing = new SwingXBuilder()
         def swing = new OusiaBuilder()
         swing.registerBeanFactory('comboBox', MaxWidthComboBox.class)
         swing.registerBeanFactory('fileBreadcrumbBar', MaxWidthBreadcrumbFileSelector.class)
         //swing.registerBeanFactory('syntaxTextArea', RSyntaxTextArea.class)

         // mac-specific settings..
         if (System.getProperty("os.name")  =~ /^Mac/) {
             System.setProperty("apple.laf.useScreenMenuBar", "true");
             System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Figurate");

             swing.edt {
                 lookAndFeel('system')
                 def menuBarUI = UIManager.get('MenuBarUI')
                 def menuUI = UIManager.get('MenuUI')
                 def menuItemUI = UIManager.get('MenuItemUI')
                 def checkBoxMenuItemUI = UIManager.get('CheckBoxMenuItemUI')
                 def radioButtonMenuItemUI = UIManager.get('RadioButtonMenuItemUI')
                 def popupMenuUI = UIManager.get('PopupMenuUI')
                 
//                 lookAndFeel('seaglass', 'substance6', 'system')
                 lookAndFeel('substance-nebula')
                 UIManager.put('MenuBarUI', menuBarUI)
                 UIManager.put('MenuUI', menuUI)
                 UIManager.put('MenuItemUI', menuItemUI)
                 UIManager.put('CheckBoxMenuItemUI', checkBoxMenuItemUI)
                 UIManager.put('RadioButtonMenuItemUI', radioButtonMenuItemUI)
                 UIManager.put('PopupMenuUI', popupMenuUI)
             }
         }
         else {
             swing.edt {
//                 lookAndFeel('seaglass', 'substance5', 'system')
//                 lookAndFeel('substance6')
                 lookAndFeel('substance-office-blue-2007')
             }
         }

         def headingFont = new Font('Arial', Font.PLAIN, 14)
         def textFont = new Font('Courier', Font.PLAIN, 12)
         def newFileCount = 0
         def navController = new NavController()
         def newTab = { tabFile ->
//             def breadcrumbBar = new BreadcrumbFileSelector()
//             def userDir = new File(System.getProperty("user.dir"))
//             breadcrumbBar.setPath(userDir)
             
             if (!tabFile) {
                 tabFile = new File(FileSystemView.fileSystemView.homeDirectory, "Unsaved File ${++newFileCount}")
             }

             RSyntaxTextArea textArea = new RSyntaxTextArea();
             
             //@Bindable String tabName = 'New Tab'
             def newPanel = swing.panel(name: tabFile.name, id: tabFile.absolutePath) {//,
//                     tabIcon: FileSystemView.fileSystemView.getSystemIcon(tabFile)) {
                     borderLayout()
//                     widget(breadcrumbBar, constraints: BorderLayout.NORTH)
//                     panel(constraints: BorderLayout.WEST) {
//                     splitPane(oneTouchExpandable: true, dividerLocation: 0) {
//                         scrollPane(constraints: "left", border: null) {
//                             list(id: 'fileList')
//                             fileList.cellRenderer = new FileListCellRenderer()
//                             fileList.valueChanged = {
//                                 if (fileList.selectedValue) {
//                                     def selectedFile = fileList.selectedValue new File(userDir, fileList.selectedValue)
//                                     if (selectedFile.isDirectory()) {
//                                         fileList.selectedIndex = -1
//                                         breadcrumbBar.setPath(selectedFile)
//                                     }
//                                     else {
//                                         editPane.text = selectedFile.text
//                                         editPane.caretPosition = 0
//                                         tab0.name = fileList.selectedValue
//                                         tab0.invalidate()
//                                         tab0.repaint()
//                                     }
//                                 }
//                                 else {
//                                     editPane.text = null
//                                 }
//                             }
//                         }

                    //syntaxTextArea(id: 'textArea', marginLineEnabled: true, whitespaceVisible: true, font: textFont)
                    //textArea.marginLineColor = Color.RED
                        if (tabFile.name =~ /\.java$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_JAVA
                        }
                        else if (tabFile.name =~ /\.groovy$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_GROOVY
                        }
                        else if (tabFile.name =~ /(?i)\.(properties|ini)$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE
                        }
                        else if (tabFile.name =~ /\.(xml|xsl|xsd|rdf|xul|svg)$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_XML
//                            textArea.closeMarkupTags = true
                        }
                        else if (tabFile.name =~ /\.(html|htm)$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_HTML
//                            textArea.closeMarkupTags = true
                        }
                        else if (tabFile.name =~ /\.css$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_CSS
                        }
                        else if (tabFile.name =~ /\.sql$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_SQL
                        }
                        else if (tabFile.name =~ /\.js$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT
                        }
                        else if (tabFile.name =~ /(?i)\.(bat|cmd)$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH
                        }
                        else if (tabFile.name =~ /\.(sh|.sh|.*rc|bash.*|profile)$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL
                        }
                        else if (tabFile.name =~ /\.rb$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_RUBY
                        }
                        else if (tabFile.name =~ /\.py$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_PYTHON
                        }
                        else if (tabFile.name =~ /\.php$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_PHP
                        }
                        else if (tabFile.name =~ /\.jsp$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_JSP
                        }
                        else if (tabFile.name =~ /^Makefile/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_MAKEFILE
                        }
                        else if (tabFile.name =~ /(?i)\.(cpp|cxx|h)$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS
                        }
                        else if (tabFile.name =~ /(?i)\.cs$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_CSHARP
                        }
                        else if (tabFile.name =~ /(?i)\.c$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_C
                        }
                        else if (tabFile.name =~ /(?i)\.pl$/) {
                            textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_PERL
                        }
//                        else {
//                            textArea.whitespaceVisible = true
//                        }
                    textArea.marginLineEnabled = true
                    textArea.font = textFont
                    textArea.markAllHighlightColor = new Color(textArea.markAllHighlightColor.red, textArea.markAllHighlightColor.green, textArea.markAllHighlightColor.blue, 64)
                        textArea.addHyperlinkListener(new HyperlinkBrowser())
                        RTextScrollPane sp = new RTextScrollPane(textArea);
                        sp.gutter.bookmarkingEnabled = true
                        sp.gutter.bookmarkIcon = imageIcon('/bookmark.png', id: 'bookmarkIcon')
                        //widget(sp)
                        
                        // add layer..
                        JXLayer<RTextScrollPane> layer = new JXLayer<RTextScrollPane>(sp)
                        layer.setUI(new RTextAreaLayerUI())
                        widget(layer)

                        doLater {
                            if (tabFile.exists()) {
                                textArea.text = tabFile.text
                                textArea.caretPosition = 0
                                textArea.discardAllEdits()
                            }
                        }
                        
                        textArea.focusGained = {
                            splitPane.dividerLocation = 0
                        }
                        
                        textArea.caretUpdate = {
                            def line = textArea.getLineOfOffset(textArea.caretPosition) + 1
                            def column = textArea.caretPosition - textArea.getLineStartOffset(line - 1)
                            def lineCount = textArea.lineCount
                            def lineLength = textArea.getLineEndOffset(line - 1) - textArea.getLineStartOffset(line - 1)
                            caretPosLabel.text = "${line}:${column} (${lineCount}:${lineLength})"
                            //updateCaretPosLabel(textArea, caretPosLabel)
                        }
                        /* XXX: this is buggy..
                        textArea.keyPressed = { e ->
                            if (e.keyCode == KeyEvent.VK_SHIFT && textArea.selectedText) {
                                textArea.markAll("(?<=\\W)${textArea.selectedText}(?=\\W)", true, false, true)
                            }
                        }
                        textArea.keyReleased = { e ->
                            if (e.keyCode == KeyEvent.VK_SHIFT && textArea.selectedText) {
                                textArea.clearMarkAllHighlights()
                            }
                        }
                        */
                        
                        bind(source: viewWordWrap, sourceProperty:'selected', target: textArea, targetProperty: 'lineWrap')
                        bind(source: viewWhitespace, sourceProperty:'selected', target: textArea, targetProperty: 'whitespaceVisible')
                        bind(source: viewLineNumbers, sourceProperty:'selected', target: sp, targetProperty: 'lineNumbersEnabled')

                        //sp.gutter.addLineTrackingIcon(0, imageIcon('F:/images/icons/logo.png'))
                        /*
                    }
                    else {
                         scrollPane(border: null) {
                             editorPane(id: 'editPane', font: textFont)
                             editPane.editorKit = new NumberedEditorKit()
                             def lineHighlighter = new LineHighlightPainter(new Color(230, 230, 230))
                             editPane.caretUpdate = { event ->
                                 def posStart = min(Utilities.getRowStart(editPane, event.dot), Utilities.getRowStart(editPane, event.mark))
                                 def posEnd = max(Utilities.getRowEnd(editPane, event.dot), Utilities.getRowEnd(editPane, event.mark))
                                 
                                 //Element elem = Utilities.getParagraphElement(editPane, event.dot)
                                 //posStart = elem.startOffset
                                 //posEnd = elem.endOffset
                                 
                                 def vetoHighlight = false
                                 //println posStart + '-' + posEnd
                                 for (highlight in editPane.highlighter.highlights) {
                                   if (highlight.painter == lineHighlighter) {
                                     editPane.highlighter.removeHighlight(highlight)
                                   }
                                   //else if (highlight.painter.startOffset <= posStart || highlight.painter.endOffset >= posEnd) {
                                   //    vetoHighlight = true
                                   //}
                                 }
                                 //println vetoHighlight
                                 if (!vetoHighlight) {
                                     editPane.highlighter.addHighlight(posStart, posEnd, lineHighlighter)
                                 }
                             }
                         }
                         doLater {
                             if (tabFile.exists()) {
                                 editPane.text = tabFile.text
                                 editPane.caretPosition = 0
                             }
                         }
                     }
                     */
//                  def fileModel = new DefaultListModel()
//                  for (file in userDir.listFiles()) {
//                    fileModel.addElement(file)
//                  }
//                  fileList.setModel(fileModel)
//             breadcrumbBar.model.addPathListener(new BreadcrumbPathListenerImpl({ //event -> println "${event}" }))
//                    swing.edt() {
//                    userDir = breadcrumbBar.model.getItem(breadcrumbBar.model.itemCount - 1).data
//                  fileModel = new DefaultListModel()
//                  for (file in userDir.listFiles()) {
//                    fileModel.addElement(file)
//                  }
//                    fileList.selectedIndex = -1
//                  fileList.setModel(fileModel)
//                }
//             }))
//                 }
             }
             
             swing.doLater {

                 // record changes for modified flag..
                 EditListener editListener = new EditListener(newPanel)
                 textArea.document.addUndoableEditListener(editListener);
                 //editListener.discardAllEdits()
                 textArea.document.addDocumentListener(editListener)
             }
             
             newPanel.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, true)
             newPanel.putClientProperty("figurate.file", tabFile)
             newPanel.putClientProperty("figurate.textArea", textArea)
             return newPanel
         }
         
         def openTab = { tabs, file ->
             if (file) {
                 if (tabs.tabCount > 0) {
                     for (i in 0..tabs.tabCount - 1) {
                         if (tabs.getComponentAt(i).getClientProperty('figurate.file').absolutePath == file.absolutePath) {
                             navController.addMark(tabs.selectedComponent)
                             tabs.selectedComponent = tabs.getComponentAt(i)
                             return
                         }
                     }
                 }
                 
                 def editor = newTab(file)
                 tabs.add(editor)
                 def tabIndex = tabs.indexOfComponent(editor)
//                 tabs.setIconAt(tabIndex, new PaddedIcon(FileSystemView.fileSystemView.getSystemIcon(file), 16, 18))
                 tabs.setToolTipTextAt(tabIndex, file.absolutePath)

                 //def tabPopupMenu = swing.popupMenu {
                 //    checkBoxMenuItem(text: 'Show Title')
                 //}
                 //tabs.getTabComponentAt(tabIndex).mouseClicked {
                 //    println 'Mouse cliked'
                 //    tabPopupMenu.show(tabs.getTabComponentAt(tabIndex), 0, 0)
                 //}
                 
                 /*
                 swing.bind(source: viewTabNames, sourceProperty:'selected', converter: {
                     println "Show tab titles: ${it}"
                     if (it) {
                         tabs.setTitleAt(tabIndex, 'Test')
                     }
                     else {
                         tabs.setTitleAt(tabIndex, null)
                     }
                 })
                 */
                 
                 navController.addMark(tabs.selectedComponent)
                 tabs.selectedComponent = editor
             }
         }
         
         def updatePath = { breadcrumbBar, pathField, newPath ->
             breadcrumbBar.path = newPath
             if (pathField.selectedItem != newPath) {
                 pathField.model.removeElement(newPath)
                 pathField.model.insertElementAt(newPath, 0)
                 pathField.selectedItem = newPath
             }
         }

        def updateCaretPosLabel = { textArea, caretPosLabel ->
            def line = textArea.getLineOfOffset(textArea.caretPosition) + 1
            def column = textArea.caretPosition - textArea.getLineStartOffset(line - 1)
            def lineCount = textArea.lineCount
            def lineLength = textArea.getLineEndOffset(line - 1) - textArea.getLineStartOffset(line - 1)
            caretPosLabel.text = "${line}:${column} (${lineCount}:${lineLength})"
        }
        
        def closeCurrentTab = { tabs ->
            if (tabs.selectedIndex > 0) {
                tabs.removeTabAt tabs.selectedIndex
            }
        }
        
        def closeOtherTabs = { tabs ->
            if (tabs.tabCount > 1) {
                for (index in (tabs.tabCount - 1)..1) {
                    if (index != tabs.selectedIndex) {
                        tabs.removeTabAt index
                    }
                }
            }
        }
        
        def closeAllTabs = { tabs ->
            if (tabs.tabCount > 1) {
                for (index in (tabs.tabCount - 1)..1) {
                    tabs.removeTabAt index
                }
            }
        }
        
         swing.edt {
             frame(title: 'Figurate', id: 'figurateFrame', defaultCloseOperation: JFrame.DO_NOTHING_ON_CLOSE,
                     size: [800, 600], show: false, locationRelativeTo: null, iconImage: imageIcon('/logo.png', id: 'logoIcon').image) {
             
//                 lookAndFeel('substance5', 'system')
                 
                 actions() {
                     action(id: 'newFileAction', name: 'New', accelerator: shortcut('N'), closure: {
                         doLater {
                             def tab = newTab()
                             tabs.add(tab)
//                             tabs.setIconAt(tabs.indexOfComponent(tab), FileSystemView.fileSystemView.getSystemIcon(file))
//                             tabs.setToolTipTextAt(tabs.indexOfComponent(tab), file.absolutePath)
                             tabs.selectedComponent = tab
                         }
                     })
                     action(id: 'openFileAction', name: 'Open', accelerator: shortcut('O'), closure: {
                         if (chooser.showOpenDialog() == JFileChooser.APPROVE_OPTION) {
                             doLater {
                                //def tab = newTab(chooser.selectedFile)
                                //tabs.add(tab)
                                //tabs.setIconAt(tabs.indexOfComponent(tab), FileSystemView.fileSystemView.getSystemIcon(chooser.selectedFile))
                                //tabs.selectedComponent = tab
                                openTab(tabs, chooser.selectedFile)
                             }
                         }
                     })
                     action(id: 'saveFileAction', name: 'Save', accelerator: shortcut('S'), closure: {
                         def editor = navController.currentMark.editor
                         if (editor.getClientProperty('figurate.file').exists()) {
                             editor.getClientProperty('figurate.file').text = editor.getClientProperty('figurate.textArea').text
                         }
                         else if (chooser.showSaveDialog() == JFileChooser.APPROVE_OPTION) {
                             chooser.selectedFile.text = editor.getClientProperty('figurate.textArea').text
                             editor.putClientProperty('figurate.file', chooser.selectedFile)
                             editor.name = chooser.selectedFile.name
                             tabs.setTitleAt(tabs.selectedIndex, editor.name)
                         }
                     })
                     action(id: 'saveAsFileAction', name: 'Save As..', closure: {
                         def editor = navController.currentMark.editor
                         if (editor.getClientProperty('figurate.file').exists()) {
                             chooser.selectedFile = editor.getClientProperty('figurate.file')
                         }
                         if (chooser.showSaveDialog() == JFileChooser.APPROVE_OPTION) {
                             chooser.selectedFile.text = editor.getClientProperty('figurate.textArea').text
                             editor.putClientProperty('figurate.file', chooser.selectedFile)
                             editor.name = chooser.selectedFile.name
                             tabs.setTitleAt(tabs.selectedIndex, editor.name)
                         }
                     })
                     action(id: 'saveCopyFileAction', name: 'Save a Copy..', closure: {
                         def editor = navController.currentMark.editor
                         if (editor.getClientProperty('figurate.file').exists()) {
                             chooser.selectedFile = editor.getClientProperty('figurate.file')
                         }
                         if (chooser.showSaveDialog() == JFileChooser.APPROVE_OPTION) {
                             chooser.selectedFile.text = editor.getClientProperty('figurate.textArea').text
                         }
                     })
                     action(id: 'closeTabAction', name: 'Close Tab', accelerator: shortcut('W'), closure: { closeCurrentTab(tabs) })
                     action(id: 'closeOtherTabsAction', name: 'Close Other Tabs', closure: { closeOtherTabs(tabs) })
                     action(id: 'closeAllTabsAction', name: 'Close All Tabs', accelerator: shortcut('shift W'), closure: { closeAllTabs(tabs) })

                     action(id: 'printAction', name: 'Print', accelerator: shortcut('P'))
                     action(id: 'exitAction', name: 'Exit', accelerator: shortcut('Q'), closure: { close(figurateFrame, true) })
                     
                     def editorKitActions = [:]
                     for (action in new RSyntaxTextArea().actions) {
                         editorKitActions.put(action.getValue(Action.NAME), action)
                     }

                     action(new IncreaseFontSizeAction(), id: 'increaseFontAction', name: 'Increase Font Size', accelerator: shortcut(KeyEvent.VK_EQUALS))
                     action(new DecreaseFontSizeAction(), id: 'decreaseFontAction', name: 'Decrease Font Size', accelerator: shortcut(KeyEvent.VK_MINUS))
                     
                     action(editorKitActions.get(RTextAreaEditorKit.rtaUpperSelectionCaseAction), id: 'upperCaseAction', name: 'Upper Case', accelerator: shortcut("shift U"))
                     action(editorKitActions.get(RTextAreaEditorKit.rtaLowerSelectionCaseAction), id: 'lowerCaseAction', name: 'Lower Case', accelerator: shortcut("shift L"))
                     action(editorKitActions.get(RTextAreaEditorKit.rtaInvertSelectionCaseAction), id: 'invertCaseAction', name: 'Invert Case', accelerator: shortcut("shift I"))
                     
                     action(new BeginRecordingMacroAction(), id: 'beginMacroAction', name: 'Begin Recording')
                     action(editorKitActions.get(RTextAreaEditorKit.rtaEndRecordingMacroAction), id: 'endMacroAction', name: 'End Recording')
                     action(editorKitActions.get(RTextAreaEditorKit.rtaPlaybackLastMacroAction), id: 'playLastMacroAction', name: 'Playback Last', accelerator: shortcut("shift P"))

                     action(new TimeDateAction(), id: 'timeDateAction', name: 'Date / Time')
                     
                     action(id: 'onlineHelpAction', name: 'Online Help', accelerator: 'F1', closure: { Desktop.desktop.browse(URI.create('http://basetools.org/figurate')) })
                     action(id: 'showTipsAction', name: 'Tips', closure: { tips.showDialog(figurateFrame) })
                     action(id: 'aboutAction', name: 'About', closure: {
                         dialog(title: 'About Figurate', size: [350, 250], show: true, owner: figurateFrame, modal: true, locationRelativeTo: figurateFrame) {
                             borderLayout()
                             label(text: 'Figurate 1.0', constraints: BorderLayout.NORTH, border: emptyBorder(10))
                             panel(constraints: BorderLayout.CENTER, border: emptyBorder(10)) {
                                 borderLayout()
                                 scrollPane(horizontalScrollBarPolicy: JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, border: null) {
                                     table() {
                                         def systemProps = []
                                         for (propName in System.properties.keySet()) {
                                             systemProps.add([property: propName, value: System.properties.getProperty(propName)])
                                         }
                                         tableModel(list: systemProps) {
                                             propertyColumn(header:'Property', propertyName:'property')
                                             propertyColumn(header:'Value', propertyName:'value')
                                         }
                                     }
                                 }
                             }
                         }
                     })
                 }
                 
                 fileChooser(id: 'chooser')
                 
//                 tipOfTheDay(id: 'tips', model: defaultTipModel(tips: [
//                     defaultTip(name: 'test', tip: '<html><em>testing</em>')
//                 ]))

                 menuBar() {
                     menu(text: "File", mnemonic: 'F') {
                         menuItem(newFileAction)
                         menuItem(openFileAction)
                         separator()
                         menuItem(closeTabAction)
                         menuItem(text: "Close Other Tabs")
                         menuItem(closeAllTabsAction)
                         separator()
                         menuItem(saveFileAction)
                         menuItem(saveAsFileAction)
                         menuItem(saveCopyFileAction)
                         menuItem(text: "Save All")
                         separator()
                         menuItem(printAction)
                         separator()
                         menuItem(exitAction)
                     }
                     menu(text: "Edit", mnemonic: 'E') {
                         // XXX: hack to initialise text actions..
                         new RTextArea()
                         menuItem(RTextArea.getAction(RTextArea.UNDO_ACTION))
                         menuItem(RTextArea.getAction(RTextArea.REDO_ACTION))
                         separator()
                         menuItem(RTextArea.getAction(RTextArea.CUT_ACTION))
                         menuItem(RTextArea.getAction(RTextArea.COPY_ACTION))
                         menuItem(RTextArea.getAction(RTextArea.PASTE_ACTION))
                         menuItem(RTextArea.getAction(RTextArea.DELETE_ACTION))
                         separator()
                         menuItem(RTextArea.getAction(RTextArea.SELECT_ALL_ACTION))
                         separator()
                         menuItem(text: "Preferences")
                     }
                     menu(text: "View", mnemonic: 'V') {
                         menuItem(increaseFontAction)
                         menuItem(decreaseFontAction)
                         separator()
                         checkBoxMenuItem(text: "Word Wrap", id: 'viewWordWrap')
                         checkBoxMenuItem(text: "Whitespace", id: 'viewWhitespace')
                         checkBoxMenuItem(text: "Line Numbers", id: 'viewLineNumbers')
                         checkBoxMenuItem(text: "Tab Names", id: 'viewTabNames')
                         separator()
                         checkBoxMenuItem(text: "Status Bar", id: 'viewStatusBar')
                     }
                     menu(text: "Tools", mnemonic: 'T') {
                         menu(text: "Transform") {
                             menuItem(upperCaseAction)
                             menuItem(lowerCaseAction)
                             menuItem(invertCaseAction)
                         }
                         menu(text: "Insert") {
                             menuItem(timeDateAction)
                         }
                         menu(text: "Macro") {
                             menuItem(beginMacroAction)
                             menuItem(endMacroAction)
                             menuItem(playLastMacroAction)
                         }
                     }
                     menu(text: "Help", mnemonic: 'H') {
                         menuItem(onlineHelpAction)
                         menuItem(showTipsAction)
                     separator()
                         menuItem(aboutAction)
                     }
                 }
                 
                 borderLayout()
                 
                 //def breadcrumbBar = new BreadcrumbFileSelector()
                 //fileBreadcrumbBar(id: 'breadcrumbBar')
                 
                 def userDir = FileSystemView.fileSystemView.homeDirectory //new File(System.getProperty("user.dir"))
                 //breadcrumbBar.path = userDir
                 
                 hbox(constraints: BorderLayout.NORTH, border:emptyBorder([5, 3, 3, 3])) {
                     //flowLayout(alignment: FlowLayout.LEADING)

                     def navButtons = new JCommandButtonStrip()
                     navButtons.displayState = CommandButtonDisplayState.FIT_TO_ICON
                     //navButtons.preferredSize = new java.awt.Dimension(50, 5)
                     def backIcon = SvgBatikResizableIcon.getSvgIcon(Figurate.class.getResource('/back.svg'), new java.awt.Dimension(20, 20))
                     def backButton = new JCommandButton(backIcon) //'Back')
//                     bind(source: navController, sourceProperty: 'currentMark', target: backButton, targetProperty: 'enabled', converter: { it?.previous != null })
                     //backButton enabled: bind { navController.currentMark && navController.currentMark.previous }
                     backButton.actionPerformed = { navController.setCurrentMark navController.currentMark?.previous }
                     navButtons.add(backButton)
                     
                     def forwardIcon = SvgBatikResizableIcon.getSvgIcon(Figurate.class.getResource('/forward.svg'), new java.awt.Dimension(20, 20))
                     def forwardButton = new JCommandButton(forwardIcon) //'Forward')
                     //forwardButton.enabled = bind { navController.currentMark && navController.currentMark?.next }
//                     bind(source: navController, sourceProperty: 'currentMark', target: forwardButton, targetProperty: 'enabled', converter: { it?.next != null })
                     forwardButton.actionPerformed = { navController.setCurrentMark navController.currentMark?.next }
                     navButtons.add(forwardButton)
                     widget(navButtons)
                     hstrut(5)
                     
                     def reloadIcon = SvgBatikResizableIcon.getSvgIcon(Figurate.class.getResource('/reload.svg'), new java.awt.Dimension(16, 16))
                     def reloadButton = new JCommandButton(reloadIcon) //'Reload')
                     //reloadButton.preferredSize = new java.awt.Dimension(40, 5)
                     widget(reloadButton)
                     hstrut(3)
                     
                     def findIcon = SvgBatikResizableIcon.getSvgIcon(Figurate.class.getResource('/find.svg'), new java.awt.Dimension(16, 16))
                     def findButton = new JCommandToggleButton(findIcon) //'Find')
                     //findButton.preferredSize = new java.awt.Dimension(30, 5)
                     widget(findButton)
                     hstrut(3)
                     
                     //toggleButton(id: 'showPathButton', constraints: BorderLayout.WEST, text: 'Path')
                     def pathIcon = SvgBatikResizableIcon.getSvgIcon(Figurate.class.getResource('/path.svg'), new java.awt.Dimension(16, 16))
                     def showPathButton = new JCommandToggleButton(pathIcon) //'Path')
                     //showPathButton.preferredSize = new java.awt.Dimension(30, 5)
                     
                     //def showPathButtonGroup = new CommandToggleButtonGroup()
                     //showPathButtonGroup.add(showPathButton)
                     widget(showPathButton)
                     hstrut(3)
                     
                     panel(id: 'togglePathPane') {
                         cardLayout()
                         hbox(constraints: 'breadcrumb') {
                             //borderLayout()
                             //widget(breadcrumbBar)
                             fileBreadcrumbBar(id: 'breadcrumbBar', path: userDir)
                         }
                         hbox(constraints: 'path') {
                             //borderLayout()
                             //textField(id: 'pathField', constraints: 'path')
                             //def pathFieldModel = new DefaultComboBoxModel()
                             comboBox(id: 'pathField', editable: true, renderer: new PathListCellRenderer()) //, model: pathFieldModel)
                             //def pathField = new MaxWidthComboBox()
                             //pathField.editable = true
                             //pathField.renderer = new PathListCellRenderer()
                             pathField.putClientProperty(LafWidget.TEXT_SELECT_ON_FOCUS, true)
                             pathField.putClientProperty(LafWidget.TEXT_FLIP_SELECT_ON_ESCAPE, true)
                             pathField.putClientProperty(LafWidget.TEXT_EDIT_CONTEXT_MENU, true)
                             widget(pathField)
                             //pathField.maximumSize = new java.awt.Dimension(Short.MAX_VALUE, 20)
                             pathField.actionPerformed = {
                                 if (pathField.selectedItem) {
                                     def newPath = pathField.selectedItem //new File(pathField.editor.item)
                                     if (newPath instanceof String) {
                                         newPath = new File(newPath)
                                     }
                                     if (newPath.exists() && breadcrumbBar.model.getItem(breadcrumbBar.model.itemCount - 1).data != newPath) {
                                         updatePath(breadcrumbBar, pathField, newPath)
                                         //pathField.model.removeElement(newPath)
                                         //pathField.model.insertElementAt(newPath, 0)
                                         //pathField.selectedItem = newPath
                                         //breadcrumbBar.path = newPath
                                     }
                                     else {
                                         pathField.selectedItem = breadcrumbBar.model.getItem(breadcrumbBar.model.itemCount - 1).data
                                     }
                                 }
                             }
                         }
                     }
                     showPathButton.putClientProperty(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, true)
                     showPathButton.actionPerformed = {
                         if (showPathButton.actionModel.selected) {
                             togglePathPane.layout.show(togglePathPane, 'path')
                         }
                         else {
                             togglePathPane.layout.show(togglePathPane, 'breadcrumb')
                         }                         
                     }
                 }
                 
                 splitPane(id: 'splitPane', oneTouchExpandable: true, dividerLocation: 0) {
                     tabbedPane(constraints: "left", tabPlacement: JTabbedPane.BOTTOM, id: 'navTabs') {
                         panel(name: 'File System') {
                             borderLayout()
                             label(text: 'Folder Contents', constraints: BorderLayout.NORTH)
                             scrollPane(border: null) {
                                 list(id: 'fileList')
                                 fileList.cellRenderer = new FileListCellRenderer()
//                                 fileList.autoCreateRowSorter = true
//                                 fileList.comparator = new FileComparator()
//                                 fileList.filterEnabled = true
                                 DataTipManager.get().register(fileList)
                                 def fileModel = new DefaultListModel()
                                 def files = FileSystemView.fileSystemView.getFiles(userDir, false)
//                                 def comparator = new FileComparator()
//                                 Arrays.sort(files, fileList.comparator)
                                 for (file in files) {
                                     fileModel.addElement(file)
                                 }
                                 fileList.model = fileModel
                             }
                         }
                         panel(name: 'Bookmarks') {
                             borderLayout()
                             label(text: 'Bookmarks', constraints: BorderLayout.NORTH)
                             scrollPane(border: null) {
                                 tree(id: 'bookmarkTree', rootVisible: false, )
                             }
                         }
                     }
                     navTabs.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CONTENT_BORDER_KIND, SubstanceConstants.TabContentPaneBorderKind.SINGLE_FULL)
                     tabbedPane(constraints: "right", tabLayoutPolicy: JTabbedPane.SCROLL_TAB_LAYOUT, id: 'tabs') {
                         /*
                         panel(name: 'Home', tabIcon: imageIcon('F:/images/icons/liquidicity/note.png')) {
                             borderLayout()
                             vbox(constraints: BorderLayout.CENTER) {
                                 panel(constraints: BorderLayout.CENTER, border: emptyBorder(10)) {
                                     borderLayout()
                                     label('Recent Files', font: headingFont, constraints: BorderLayout.NORTH)
                                     table(constraints: BorderLayout.CENTER)
                                 }
                                 vglue()
                             }
                             vbox(constraints: BorderLayout.EAST) {
                                 panel(constraints: BorderLayout.NORTH, border: emptyBorder(10)) {
                                     borderLayout()
                                     label('Search', font: headingFont, constraints: BorderLayout.NORTH)
                                     panel(constraints: BorderLayout.CENTER) {
                                         borderLayout()
                                         textField('Enter query', font: headingFont,  foreground: Color.LIGHT_GRAY, border: null, constraints: BorderLayout.NORTH)
                                         vbox(constraints: BorderLayout.CENTER) {
                                             hyperlink('192.168.0.1')
                                             hyperlink('hdparm=1')
                                             vglue()
                                         }
                                     }
                                 }
                                 panel(constraints: BorderLayout.SOUTH, border: emptyBorder(10)) {
                                     borderLayout()
                                     label('Tags', font: headingFont, constraints: BorderLayout.NORTH)
                                     panel(constraints: BorderLayout.CENTER) {
                                         hyperlink('network')
                                         hyperlink('font', font: headingFont)
                                         hyperlink('printer settings')
                                     }
                                 }
                                 vglue()
                             }
                         }
                         */
                         newTab()
                         navController.addMark(tabs.selectedComponent)
                     }
                     tabs.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CONTENT_BORDER_KIND, SubstanceConstants.TabContentPaneBorderKind.SINGLE_FULL)
//                     tabs.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_CALLBACK, new TabCloseCallbackImpl())
                     tabs.putClientProperty(LafWidget.TABBED_PANE_PREVIEW_PAINTER, new DefaultTabPreviewPainter())
//                     SubstanceLookAndFeel.registerTabCloseChangeListener(tabs, new VetoableTabCloseListenerImpl())
                     SubstanceLookAndFeel.registerTabCloseChangeListener(tabs, new VetoableMultipleTabCloseListenerImpl())

                     tabs.stateChanged = {
                         if (tabs.selectedComponent) {
                             navController.addMark(tabs.selectedComponent)
                             
                             def tabFile = tabs.selectedComponent.getClientProperty("figurate.file")
                            def newPath = tabFile.parentFile
                             if (newPath.exists() && breadcrumbBar.model.getItem(breadcrumbBar.model.itemCount - 1).data != newPath) {
                                 //breadcrumbBar.path = newPath
                                 //pathField.model.removeElement(newPath)
                                 //pathField.model.insertElementAt(newPath, 0)
                                 //pathField.selectedItem = newPath
                                 updatePath(breadcrumbBar, pathField, newPath)
                             }
                             
                             // update syntax status..
                             syntaxLabel.text = tabs.selectedComponent.getClientProperty("figurate.textArea").syntaxEditingStyle
                             updateCaretPosLabel(tabs.selectedComponent.getClientProperty("figurate.textArea"), caretPosLabel)
                             
                             if (tabFile.exists()) {
                                 lastModLabel.text = new Date(tabFile.lastModified()).dateTimeString
                             }
                             else {
                                 lastModLabel.text = '<unsaved>'
                             }
                         }
                         else {
                             breadcrumbBar.path = FileSystemView.fileSystemView.homeDirectory //new File(System.getProperty("user.dir"))
                             syntaxLabel.text = 'none'
                             lastModLabel.text = ''
                         }
                     }
                 }
                 breadcrumbBar.model.addPathListener(new BreadcrumbPathListenerImpl({
                     doLater() {
                         userDir = breadcrumbBar.model.getItem(breadcrumbBar.model.itemCount - 1).data
                         def fileModel = new DefaultListModel()
                         def files = FileSystemView.fileSystemView.getFiles(userDir, false)
//                         Arrays.sort(files, fileList.comparator)
                         for (file in files) {
                             fileModel.addElement(file)
                         }
                         fileList.selectedIndex = -1
                         fileList.setModel(fileModel)
                         //pathField.text = userDir.absolutePath
                         if (pathField.selectedItem != userDir) {
                             pathField.model.removeElement(userDir)
                             pathField.model.insertElementAt(userDir, 0)
                             pathField.selectedItem = userDir
                         }
                         
                         if (splitPane.dividerLocation == 0) {
                             //splitPane.dividerLocation = fileList.preferredSize.width + 20
                             splitPane.resetToPreferredSizes()
                         }
                     }
                 }))
                 
                 fileList.valueChanged = { e ->
                     if (fileList.selectedValue && !e.valueIsAdjusting) {
                         def selectedFile = fileList.selectedValue
                         if (selectedFile.isDirectory()) {
                             fileList.selectedIndex = -1
                             //breadcrumbBar.path = selectedFile
                             //pathField.model.removeElement(selectedFile)
                             //pathField.model.insertElementAt(selectedFile, 0)
                             //pathField.selectedItem = selectedFile
                             updatePath(breadcrumbBar, pathField, selectedFile)
                         }
                         else {
//                             editPane.text = selectedFile.text
//                             editPane.caretPosition = 0
                            //def tab = newTab(selectedFile)
                            //tabs.add(tab)
                            //tabs.setIconAt(tabs.indexOfComponent(tab), FileSystemView.fileSystemView.getSystemIcon(selectedFile))
                            //tabs.selectedComponent = tab
                            openTab(tabs, selectedFile)
//                            tabs.add(panel(name: selectedFile.name, id: selectedFile.absolutePath,
//                                    tabIcon: FileSystemView.fileSystemView.getSystemIcon(selectedFile)))
                         }
                     }
//                     else {
//                         editPane.text = null
//                     }
                 }
                 
                 if (SystemTray.isSupported()) {
                     TrayIcon trayIcon = new TrayIcon(imageIcon('/bookmark.gif').image, 'Figurate')
                     trayIcon.imageAutoSize = false
                     trayIcon.mousePressed = { event ->
                         if (event.button == MouseEvent.BUTTON1) {
                             figurateFrame.visible = true
                         }
                     }
                     
                     PopupMenu popupMenu = new PopupMenu('Figurate')
                     MenuItem openMenuItem = new MenuItem('Open')
                     openMenuItem.actionPerformed = {
                        figurateFrame.visible = true
                     }
                     popupMenu.add(openMenuItem)
                     //openMenuItem.addNotify()
                     //openMenuItem.font = openMenuItem.font.deriveFont(Font.BOLD)
                     popupMenu.addSeparator()
                     MenuItem exitMenuItem = new MenuItem('Exit')
                     exitMenuItem.actionPerformed = {
                         close(figurateFrame, true)
                     }
                     popupMenu.add(exitMenuItem)
                     trayIcon.popupMenu = popupMenu
                     
                     SystemTray.systemTray.add(trayIcon)
                 }
                 
                 JXStatusBar fStatusBar = new JXStatusBar()
                 fStatusBar.add(label(id: 'statusMessage', text: 'Ready'), new JXStatusBar.Constraint(JXStatusBar.Constraint.ResizeBehavior.FILL))
                 fStatusBar.add(label(id: 'caretPosLabel', text: '1:0'))
                 fStatusBar.add(label(id: 'syntaxLabel', text: 'text/plain')) //, constraints: new JXStatusBar.Constraint(50))
                 fStatusBar.add(label(id: 'lastModLabel', text: '<Unsaved>'))
                 widget(fStatusBar, constraints: BorderLayout.SOUTH, border:emptyBorder([5, 3, 3, 3]), id: 'fStatusBar')
                 
//                statusBar(constraints: BorderLayout.SOUTH, border:emptyBorder([5, 3, 3, 3]), id: 'fStatusBar') {
//                    label(id: 'statusMessage', text: 'Ready', constraints: new JXStatusBar.Constraint(JXStatusBar.Constraint.ResizeBehavior.FILL))
//                    label(id: 'caretPosLabel', text: '1:0')
//                    label(id: 'syntaxLabel', text: 'text/plain') //, constraints: new JXStatusBar.Constraint(50))
//                    label(id: 'lastModLabel', text: '<Unsaved>')
//                }
//                bind(source: viewStatusBar, sourceProperty:'selected', target:fStatusBar, targetProperty:'visible')
             }
//             TrackerRegistry.instance.register(figurateFrame, 'figurateFrame');
             figurateFrame.windowClosing = {
                     close(figurateFrame, !SystemTray.isSupported())
             }
             figurateFrame.windowStateChanged = {
                 if ((figurateFrame.extendedState & Frame.MAXIMIZED_BOTH) != 0) {
                     splitPane.dividerLocation = -1
                 }
             }
             figurateFrame.visible = true
         }
     }
}

class NavController {
    //def history = []
    //@Bindable Boolean hasPrevious = false
    //@Bindable Boolean hasNext = false
    @Bindable HistoryMark currentMark
    
    void addMark(def editor) {
        //println "Adding mark.. ${!history.isEmpty()}"
        def mark = new HistoryMark()
        mark.editor = editor
        mark.cursorPos = editor.getClientProperty('figurate.textArea').caretPosition
        //hasPrevious = !history.isEmpty()
        //hasNext = false
        //history.add(mark)
        mark.previous = currentMark
        if (currentMark) {
          currentMark.next = mark
        }
        setCurrentMark mark
    }
    
    void setCurrentMark(def mark) {
        currentMark = mark
    }
}

class HistoryMark {
    def editor
    def cursorPos
    def previous
    def next
}
/*
class FigurateModel {
    @Bindable Map activeDocument = null  
    List openDocuments = []  
    DocumentState state = new DocumentState()  
}  
  
@Bindable class DocumentState {  
    boolean isDirty = false  
}
*/

class BreadcrumbPathListenerImpl implements BreadcrumbPathListener {
    def closure
    
    BreadcrumbPathListenerImpl(c) {
      closure = c
    }
    
    void breadcrumbPathEvent(BreadcrumbPathEvent event) {
        closure()
    }
}
    
class LineHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        
        public LineHighlightPainter(Color colour)
        {
            super(colour);
        }
        
        void paint(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c) {
            super.paint(g, offs0, offs1, bounds, c);
        }
}

class MaxWidthComboBox extends JComboBox {

  public Dimension getMaximumSize() {
      Dimension maxSize = getPreferredSize()
      maxSize.width = Short.MAX_VALUE
      return maxSize
  }
}

class MaxWidthBreadcrumbFileSelector extends BreadcrumbFileSelector {

  public Dimension getMaximumSize() {
      Dimension maxSize = getPreferredSize()
      maxSize.width = Short.MAX_VALUE
      return maxSize
  }
}

class VetoableTabCloseListenerImpl implements VetoableTabCloseListener {
    
    public boolean vetoTabClosing(JTabbedPane tabbedPane, Component tabComponent) {
        boolean unsaved = tabComponent.getClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED)
        if (unsaved) {
            def file = tabComponent.getClientProperty("figurate.file")
            def selection = JOptionPane.showConfirmDialog(tabbedPane, "Save changes to ${file.name}?")
            if (selection == JOptionPane.YES_OPTION) {
                file.text = tabComponent.getClientProperty('figurate.textArea').text
            }
            else if (selection == JOptionPane.CANCEL_OPTION) {
                return true
            }
        }
        return false
    }
    
    public void tabClosing(JTabbedPane tabbedPane, Component tabComponent) {}
    
    public void tabClosed(JTabbedPane tabbedPane, Component tabComponent) {}
}

class VetoableMultipleTabCloseListenerImpl implements VetoableMultipleTabCloseListener {
    
    boolean vetoTabsClosing(JTabbedPane tabbedPane, Set tabComponents) {
        for (tabComponent in tabComponents) {
            boolean unsaved = tabComponent.getClientProperty(SubstanceLookAndFeel.WINDOW_MODIFIED)
            if (unsaved) {
                def file = tabComponent.getClientProperty("figurate.file")
                def selection = JOptionPane.showConfirmDialog(tabbedPane, "Save changes to ${file.name}?")
                if (selection == JOptionPane.YES_OPTION) {
                    file.text = tabComponent.getClientProperty('figurate.textArea').text
                }
                else if (selection == JOptionPane.CANCEL_OPTION) {
                    return true
                }
            }
        }
        return false
    }
    
    void tabsClosing(JTabbedPane tabbedPane, Set tabComponents) {}
    
    void tabsClosed(JTabbedPane tabbedPane, Set tabComponents) {}
}

class FigurateControl {
}

class RTextAreaLayerUI extends AbstractLayerUI<RTextScrollPane> {

    protected void paintLayer(Graphics2D g2, JXLayer<RTextScrollPane> l) {
        super.paintLayer(g2, l);
        /*
        def textArea = l.view.textArea
        if (textArea.selectedText) {
            def origColour = g2.color
            g2.color = new Color(0, 128, 0, 128)
            
            Rectangle viewRect = l.view.viewport.viewRect
            Point viewPos = l.view.viewport.location
            //println "viewport: ${l.view.viewport.x}, ${l.view.viewport.y}"
            //println "viewRect: ${viewRect}, ${viewPos}"
            //g2.fillRect(0, 0, l.getWidth(), l.getHeight());
            g2.fillRect((int) viewPos.x, (int) viewPos.y, (int) viewRect.width, (int) viewRect.height)
//            println "layer painted: ${viewRect}, ${viewPos}"

//            g2.color = origColour
            def selectRect = textArea.modelToView(textArea.selectionStart) //.add(textArea.modelToView(textArea.selectionEnd))
            println "selected rect: ${selectRect}"
            g2.clearRect((int) (viewPos.x + selectRect.x), (int) (viewPos.y + selectRect.y), (int) selectRect.width, (int) selectRect.height)
        }
        */
    }
}
