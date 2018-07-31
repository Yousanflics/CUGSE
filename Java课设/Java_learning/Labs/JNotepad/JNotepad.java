// Description:
//       A simple Windows version of Notepad
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileFilter;
import java.awt.datatransfer.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.text.Document;

class JNotepad implements ActionListener {
    JFrame mFrame;
    JTextArea mTextArea;
    File mCurrentFile;
    JFileChooser mFileChooser;
    Clipboard mClipboard;
    String mSearchString;
    int mSearchIndex;
    JPopupMenu mPopupMenu;
    FontMenu mFontMenu;

    JNotepad() {
	mClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	mSearchIndex = 0;

	mFrame = new JFrame(getFrameTitle());
	mFrame.setSize(900, 600);
	mFrame.setLayout(new BorderLayout());
	mFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	mFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    saveCommandOnExit();
		}
	    });
       
	createTextArea();
	createMenuBar();
	mFontMenu = new FontMenu(mFrame, mTextArea);
	
	mFrame.setLocationRelativeTo(null);
	mFrame.setVisible(true);
    }

    public String getFrameTitle() {
	try {
	    return mCurrentFile.getName();
	} catch (NullPointerException xException) {
	    return ("JNotepad");
	}
    }

    public void createTextArea() {
	mTextArea = new JTextArea();
	JScrollPane mScrollPane = new JScrollPane(mTextArea);
	mFrame.add(mScrollPane, BorderLayout.CENTER);
    }

    public void createMenuBar() {
	JMenuBar mMenuBar = new JMenuBar();
	
	JMenu mFileMenu = new JMenu("File");
	JMenuItem mNew = new JMenuItem("New");
	JMenuItem mOpen = new JMenuItem("Open...");
	JMenuItem mSave = new JMenuItem("Save");
	JMenuItem mSaveAs = new JMenuItem("Save As...");
	JMenuItem mPageSetup = new JMenuItem("Page Setup...");
	mPageSetup.setEnabled(false);
	JMenuItem mPrint = new JMenuItem("Print...");
	mPrint.setEnabled(false);
	JMenuItem mExit = new JMenuItem("Exit");
	mFileMenu.add(mNew);
	mFileMenu.add(mOpen);
	mFileMenu.add(mSave);
	mFileMenu.add(mSaveAs);
	mFileMenu.addSeparator();
	mFileMenu.add(mPageSetup);
	mFileMenu.add(mPrint);
	mFileMenu.addSeparator();
	mFileMenu.add(mExit);
	mMenuBar.add(mFileMenu);

	JMenu mEditMenu = new JMenu("Edit");
	JMenuItem mUndo = new JMenuItem("Undo");
	mUndo.setEnabled(false);
	JMenuItem mCut = new JMenuItem("Cut");
	JMenuItem mCopy = new JMenuItem("Copy");
	JMenuItem mPaste = new JMenuItem("Paste");
	JMenuItem mDelete = new JMenuItem("Delete");
	JMenuItem mFind = new JMenuItem("Find...");
	JMenuItem mFindNext = new JMenuItem("Find Next");
	JMenuItem mReplace = new JMenuItem("Replace...");
	mReplace.setEnabled(false);
	JMenuItem mGoTo = new JMenuItem("Go To");
	mGoTo.setEnabled(false);
	JMenuItem mSelectAll = new JMenuItem("Select All");
	mSelectAll.setEnabled(false);
	JMenuItem mTimeDate = new JMenuItem("Time/Date");
	mEditMenu.add(mUndo);
	mEditMenu.addSeparator();
	mEditMenu.add(mCut);
	mEditMenu.add(mCopy);
	mEditMenu.add(mPaste);
	mEditMenu.add(mDelete);
	mEditMenu.addSeparator();
	mEditMenu.add(mFind);
	mEditMenu.add(mFindNext);
	mEditMenu.add(mReplace);
	mEditMenu.add(mGoTo);
	mEditMenu.addSeparator();
	mEditMenu.add(mSelectAll);
	mEditMenu.add(mTimeDate);
	mMenuBar.add(mEditMenu);

	JMenu mFormatMenu = new JMenu("Format");
	JMenuItem mWordWrap = new JCheckBoxMenuItem("Word Wrap");
	JMenuItem mFont = new JMenuItem("Font...");
	mFormatMenu.add(mWordWrap);
	mFormatMenu.add(mFont);
	mMenuBar.add(mFormatMenu);
       
	JMenu mViewMenu = new JMenu("View");
	JMenuItem mStatusBar = new JMenuItem("Status Bar");
	mStatusBar.setEnabled(false);
	mViewMenu.add(mStatusBar);
	mMenuBar.add(mViewMenu);

	JMenu mHelpMenu = new JMenu("Help");
	JMenuItem mViewHelp = new JMenuItem("View Help");
	mViewHelp.setEnabled(false);
	JMenuItem mAboutJNotepad = new JMenuItem("About JNotepad");
	mHelpMenu.add(mViewHelp);
	mHelpMenu.addSeparator();
	mHelpMenu.add(mAboutJNotepad);
	mMenuBar.add(mHelpMenu);

	createPopupMenu(mCut, mCopy, mPaste);

	mFileMenu.setMnemonic(KeyEvent.VK_F);
	mNew.addActionListener(this);
	mNew.setMnemonic(KeyEvent.VK_N);
	mNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 
						   ActionEvent.CTRL_MASK));
	
	mOpen.addActionListener(this);
	mOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 
						    ActionEvent.CTRL_MASK));

	mSave.addActionListener(this);
	mSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 
						    ActionEvent.CTRL_MASK));

	mSaveAs.addActionListener(this);

	mPageSetup.setMnemonic(KeyEvent.VK_U);
	
	mPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, 
						     ActionEvent.CTRL_MASK));

	mExit.addActionListener(this);
	mExit.setMnemonic(KeyEvent.VK_X);

	mEditMenu.setMnemonic(KeyEvent.VK_E);
	mCut.addActionListener(this);
	mCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
						   ActionEvent.CTRL_MASK));

	mCopy.addActionListener(this);
	mCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
						   ActionEvent.CTRL_MASK));

	mPaste.addActionListener(this);
	mPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
						     ActionEvent.CTRL_MASK));
	
	mDelete.addActionListener(this);
	mDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0)); 

	mFind.addActionListener(this);
	mFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, 
						    ActionEvent.CTRL_MASK)); 

	mFindNext.addActionListener(this);
	mFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));

	mReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, 
						       ActionEvent.CTRL_MASK));
	
	mGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, 
						    ActionEvent.CTRL_MASK));

	mSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 
							 ActionEvent.CTRL_MASK));

	mTimeDate.addActionListener(this);
	mTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
	
	mFormatMenu.setMnemonic(KeyEvent.VK_O);
	mWordWrap.addActionListener(this);
	mWordWrap.setMnemonic(KeyEvent.VK_W);

	mFont.addActionListener(this);

	mViewMenu.setMnemonic(KeyEvent.VK_V);
	mStatusBar.setMnemonic(KeyEvent.VK_S);

	mHelpMenu.setMnemonic(KeyEvent.VK_H);
	mViewHelp.setMnemonic(KeyEvent.VK_H);
	mAboutJNotepad.addActionListener(this);
	
	mFrame.setJMenuBar(mMenuBar);
    }
    
    public void createJFileChooser() {
	mFileChooser = new JFileChooser();
	mFileChooser.addChoosableFileFilter(new FileFilter() {
		public boolean accept(File xFile) {
		    return xFile.getName().toLowerCase().endsWith(".txt")
			|| xFile.isDirectory(); 
		}

		public String getDescription() {
		    return "*.txt";
		}
	});
    }
    
    public void openFile() {
	try {
	    createJFileChooser();
	    int mUserChoice = mFileChooser.showOpenDialog(null);
	    if (mUserChoice != JFileChooser.APPROVE_OPTION) {
		JOptionPane.showMessageDialog(mFrame, "No file selected");
	    } else {
		File mFileToOpen = mFileChooser.getSelectedFile();
		Scanner mScanner = new Scanner(mFileToOpen);
		String mFileContent = "";
		while (mScanner.hasNext()) {
		    mFileContent += mScanner.nextLine() + "\n";
		}
		mTextArea.setText(mFileContent);
		mFrame.setTitle(mFileChooser.getName(mFileToOpen));
		mCurrentFile = mFileToOpen;
	    }
	} catch (IOException xException) {
	    JOptionPane.showMessageDialog(mFrame, xException);
	}
    }
    
    public void saveFile() {
	createJFileChooser();
	int mUserChoice = mFileChooser.showSaveDialog(mFrame);
	if (mUserChoice == JFileChooser.APPROVE_OPTION) {
	    File mFileToSave = mFileChooser.getSelectedFile();
	    try {
		FileWriter mFileWriter = new FileWriter(mFileToSave + ".txt");
		BufferedWriter mWriter = new BufferedWriter(mFileWriter);
		mWriter.write(mTextArea.getText());
		mWriter.close();
		mCurrentFile = mFileToSave;
		mFrame.setTitle(mFileChooser.getName(mCurrentFile));
	    } catch (IOException xException) {
		JOptionPane.showMessageDialog(mFrame, "Cannot save file...");
		mFrame.setTitle("JNotepad");
	    }
	}
    }

    public void overwriteFile() {
	try {
	    FileWriter mFileWriter = new FileWriter(mCurrentFile);
	    BufferedWriter mWriter = new BufferedWriter(mFileWriter);
	    mWriter.write(mTextArea.getText());
	    mWriter.close();
	} catch (IOException xException) {
	    JOptionPane.showMessageDialog(mFrame, "Cannot save file...");
	    mFrame.setTitle("JNotepad");
	}
    }

    public void cutCommand() {
	String mSelectedText = mTextArea.getSelectedText();
	StringSelection mClipboardString = new StringSelection(mSelectedText);
	mClipboard.setContents(mClipboardString, mClipboardString);
	mTextArea.replaceRange("", 
			       mTextArea.getSelectionStart(), 
			       mTextArea.getSelectionEnd());
    }

    public void copyCommand() {
	String mSelectedText = mTextArea.getSelectedText();
	StringSelection mClipboardString = new StringSelection(mSelectedText);
	mClipboard.setContents(mClipboardString, mClipboardString);
    }

    public void pasteCommand() {
	Transferable mClipboardData = mClipboard.getContents(this);
	try {
	    String mCurrentText = (String) mClipboardData.getTransferData(DataFlavor.stringFlavor);
	    mTextArea.replaceRange(mCurrentText, 
				   mTextArea.getSelectionStart(),
				   mTextArea.getSelectionEnd());
	} catch (Exception xException) {
	    xException.printStackTrace();
	}
    }
    
    public void deleteCommand() {
	mTextArea.replaceRange("", 
			       mTextArea.getSelectionStart(), 
			       mTextArea.getSelectionEnd());

    }
    
    public void findCommand() {
	JDialog mDialog = new JDialog(mFrame, "Find...");
	JPanel mPanel = new JPanel();
	JPanel mButtonPanel = new JPanel();
	JPanel mTextPanel = new JPanel();
	JPanel mLabelPanel = new JPanel();
	mPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	JButton mFindButton = new JButton("Find Next");
	JLabel mLabel = new JLabel("Find What: ", JLabel.LEFT);
	final JTextField mSearchBox = new JTextField(25);
	mLabelPanel.add(mLabel);
	mTextPanel.add(mSearchBox);
	mButtonPanel.add(mFindButton);
	mPanel.add(mLabelPanel, BorderLayout.WEST);
	mPanel.add(mTextPanel, BorderLayout.CENTER);
	mPanel.add(mButtonPanel, BorderLayout.EAST);
	mPanel.setPreferredSize(new Dimension(400, 50));
	mDialog.add(mPanel);
	mDialog.setBounds(200, 200, 525, 80);
	mDialog.setVisible(true);
	
	mSearchBox.requestFocusInWindow();
	JRootPane mRootPane = SwingUtilities.getRootPane(mFindButton);
	mRootPane.setDefaultButton(mFindButton);
	mFindButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent xActionEvent) {
		    mSearchString = mSearchBox.getText().toLowerCase();
		    stringLookUp(mSearchString);
		}
	    });
    }

    public void stringLookUp(String xTarget) {	 
	mTextArea.requestFocusInWindow();
	if (xTarget != null && xTarget.length() > 0) {
	    Document mDocument = mTextArea.getDocument();
	    int mLength = xTarget.length();
	    try {
		boolean mIsFound = false;
		if (mSearchIndex + mLength > mDocument.getLength()) 
		    mSearchIndex = 0;
		
		while (mSearchIndex + mLength <= mDocument.getLength()) {
		    String mMatch = mDocument.getText(mSearchIndex, 
						      mLength).toLowerCase();
		    if (mMatch.equals(xTarget)) {
			mIsFound = true;
			break;
		    }
		    mSearchIndex++;
		}

		if (mIsFound) {
		    Rectangle viewRect = mTextArea.modelToView(mSearchIndex);
		    mTextArea.scrollRectToVisible(viewRect);
		    mTextArea.setCaretPosition(mSearchIndex + mLength);
		    mTextArea.moveCaretPosition(mSearchIndex);
		    mSearchIndex += mLength;
		}
	    } catch (Exception xException) {
		xException.printStackTrace();
	    }
	}
    }

    public void dateAndTimeCommand() {
	DateFormat mDateFormat = new SimpleDateFormat("h:mm a MM/dd/yyyy");
	Date mDate = new Date();
	mTextArea.setText(mTextArea.getText() 
			  + mDateFormat.format(mDate));
    }
    
    public void wordWrapCommand() {
	if (mTextArea.getLineWrap()) 
	    mTextArea.setLineWrap(true);
	else 
	    mTextArea.setLineWrap(false);
    }

    public void createPopupMenu(JMenuItem xCut, 
				JMenuItem xCopy, 
				JMenuItem xPaste) {
	mPopupMenu = new JPopupMenu("PopupMenu");
	mPopupMenu.add(xCut);
	mPopupMenu.add(xCopy);
	mPopupMenu.add(xPaste);
	mTextArea.add(mPopupMenu);
	
	MouseListener mPopupListener = new PopupMenuListener();
	mTextArea.addMouseListener(mPopupListener);
    }

    public void saveCommandOnExit() {
	if (mCurrentFile == null && !mTextArea.getText().equals("")) {
	    int userResponse = JOptionPane.showConfirmDialog(mFrame, 
							     "Do you want to save your changes?");
	    if (userResponse == 0) {
		saveFile();
		mFrame.dispose();
	    } else if (userResponse == 1) 
		mFrame.dispose();
	}
	else 
	    mFrame.dispose();
    }

    public void actionPerformed(ActionEvent xActionEvent) {
	switch (xActionEvent.getActionCommand()) {
	case "New": 
	    new JNotepad();
	    break;
	case "Open...":
	    openFile();
	    break;
	case "Save":
	    if (mCurrentFile == null)
		saveFile();
	    else
		overwriteFile();
	    break;
	case "Save As...":
	    saveFile();
	    break;
	case "Exit":
	    System.exit(0);
	    break;
	case "Cut":
	    cutCommand();
	    break;
	case "Copy":
	    copyCommand();
	    break;
	case "Paste":
	    pasteCommand();
	    break;
	case "Delete":
	    deleteCommand();
	    break;
	case "Find...":
	    findCommand();
	    break;
	case "Find Next":
	    stringLookUp(mSearchString);
	    break;
	case "Time/Date":
	    dateAndTimeCommand();
	    break;
	case "Word Wrap":
	    wordWrapCommand();
	    break;
	case "Font...":
	    mFontMenu.mDialog.setVisible(true);
	    break;
	case "About JNotepad":
	    JOptionPane.showMessageDialog(mFrame, "(c) Brian Ngo");
	default:
	    break;
	}
    }

    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    new JNotepad();
		}
	    });
    }

        private class PopupMenuListener extends MouseAdapter {
	public void mousePressed(MouseEvent xEvent) {
	    maybeShowPopup(xEvent);
	}

	public void mouseReleased(MouseEvent xEvent) {
	    maybeShowPopup(xEvent);
	}

	private void maybeShowPopup(MouseEvent xEvent) {
	    if (xEvent.isPopupTrigger()) {
		mPopupMenu.show(xEvent.getComponent(),
				xEvent.getX(), 
				xEvent.getY());
	    }
	}
    }
}
