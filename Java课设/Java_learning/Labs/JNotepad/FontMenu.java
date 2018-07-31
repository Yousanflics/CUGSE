


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class FontMenu {
    JDialog mDialog;
    JTextField mFontTextField;
    JList mFontList;
    String[] mFontNames;
    JPanel mFontPanel;
    JTextField mSizeTextField;
    JList mSizeList;
    String[] mSize;
    JPanel mSizePanel;
    JTextField mStyleTextField;
    JList mStyleList;
    String[] mStyle = {"Plain", "Italic", "Bold", "Bold Italic"};
    JPanel mTextColorPanel;
    JTextField mTextColorTextField;
    JList mTextColorList;
    Color[] mColors = {Color.BLACK, Color.BLUE, Color.RED, Color.WHITE, 
		       Color.YELLOW, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
		       Color.MAGENTA, Color.ORANGE, Color.PINK, Color.CYAN, 
		       Color.DARK_GRAY};
    String[] mColorsText = {"Black", "Blue", "Red", "White", "Yellow",
			    "Gray", "Green", "Light Gray", "Magenta", 
			    "Orange", "Pink", "Cyan", "Dark Gray"};
    JPanel mBackgroundColorPanel;
    JTextField mBackgroundColorTextField;
    JList mBackgroundColorList;
    JPanel mStylePanel;
    JLabel mLabel;
    JPanel mBottomPanel;
    JPanel mLabelPanel;
    String mCurrentFont;
    int mCurrentStyle;
    int mCurrentSize;
    JTextArea mTextArea;
    Color mDefaultBackgroundColor = Color.WHITE,
	mDefaultTextColor = Color.BLACK;
    
    FontMenu(JFrame xFrame, JTextArea xTextArea) {
	mCurrentFont = null;
	mCurrentStyle = Font.PLAIN;
	mCurrentSize = 12;
	
	mTextArea = xTextArea;

	mDialog = new JDialog(xFrame, "Font...", true);
	mDialog.setSize(600, 350);
	mDialog.setLayout(new BorderLayout());
	mDialog.setLocationRelativeTo(xFrame);

	JPanel mOptionPanel = new JPanel();
	mOptionPanel.setLayout(new FlowLayout());
	mOptionPanel.setPreferredSize(new Dimension(500, 200));
	JPanel mColorPanel = new JPanel();
	mColorPanel.setLayout(new FlowLayout());
	mColorPanel.setPreferredSize(new Dimension(500, 200));
	
	createBottomPanel();
	createFontPanel();
	createSizePanel();
	createStylePanel();
	createTextColorPanel();
	createBackgroundColorPanel();
	
	mOptionPanel.add(mFontPanel, BorderLayout.CENTER);
	mOptionPanel.add(mSizePanel, BorderLayout.CENTER);
	mOptionPanel.add(mStylePanel, BorderLayout.CENTER);
	mOptionPanel.add(mTextColorPanel);
	mOptionPanel.add(mBackgroundColorPanel);

	mDialog.add(mOptionPanel, BorderLayout.NORTH);
	mDialog.add(mBottomPanel, BorderLayout.CENTER);

    }
    
    public void createBottomPanel() {
	mBottomPanel = new JPanel(new FlowLayout());
	mLabelPanel = new JPanel(new BorderLayout());
	mLabelPanel.setPreferredSize(new Dimension(300, 100));
	JButton mOkButton = new JButton("Ok"); 
	mOkButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent xEvent) {
		    mTextArea.setFont(new Font(mCurrentFont, 
					       mCurrentStyle,
					       mCurrentSize));
		    mTextArea.setForeground(mDefaultTextColor);
		    mTextArea.setBackground(mDefaultBackgroundColor);
		    mDialog.setVisible(false);
		}
	    });
	JButton mCancelButton = new JButton("Cancel");
	mCancelButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent xEvent) {
		    mDialog.setVisible(false);
		}
	    });
	mLabel = new JLabel("AaBbYyZz");
	mLabel.setHorizontalAlignment(SwingConstants.CENTER);
	mLabelPanel.setBorder(BorderFactory.createEtchedBorder());
	mLabelPanel.add(mLabel);
	mBottomPanel.add(mOkButton);
	mBottomPanel.add(mCancelButton);
	mBottomPanel.add(mLabelPanel);
    }

    public void createFontPanel() {
	mFontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	JLabel mFontLabel = new JLabel("Font");
	mFontList = new JList<>(mFontNames);
	JScrollPane mFontScrollPane = new JScrollPane(mFontList);
	mFontTextField = new JTextField(mFontScrollPane.getViewport().getSize().width);
	mFontList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent xEvent) {
		    int index = mFontList.getSelectedIndex();
		    mCurrentFont = mFontNames[index];
		    mFontTextField.setText(mCurrentFont);
		    mLabel.setFont(new Font(mCurrentFont, 
					    mCurrentStyle,
					    mCurrentSize));
		}
	    });
	mFontPanel = new JPanel(new BorderLayout());
	mFontPanel.add(mFontLabel, BorderLayout.NORTH);
	mFontPanel.add(mFontTextField, BorderLayout.CENTER);
	mFontPanel.add(mFontScrollPane, BorderLayout.SOUTH);		
    }

    public void createSizePanel() {
	mSize = new String[60];
	for (int ii = 0; ii < 60; ++ii) 
	    mSize[ii] = ii + "";
	JLabel mSizeLabel = new JLabel("Size");
	mSizeList = new JList<>(mSize);
	JScrollPane mSizeScrollPane = new JScrollPane(mSizeList);
	mSizeTextField = new JTextField(mSizeScrollPane.getViewport().getSize().width);
	mSizeList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent xEvent) {
		    int index = mSizeList.getSelectedIndex();
		    mCurrentSize = Integer.parseInt(mSize[index]);
		    mSizeTextField.setText(mSize[index]);
		    mLabel.setFont(new Font(mCurrentFont, 
					    mCurrentStyle,
					    mCurrentSize));
		}
	    });
	mSizePanel = new JPanel(new BorderLayout());
	mSizePanel.add(mSizeLabel, BorderLayout.NORTH);
	mSizePanel.add(mSizeTextField, BorderLayout.CENTER);
	mSizePanel.add(mSizeScrollPane, BorderLayout.SOUTH);
    }

    public void createStylePanel() {
	JLabel mStyleLabel = new JLabel("Style");
        mStyleList = new JList<>(mStyle);
	JScrollPane mStyleScrollPane = new JScrollPane(mStyleList);
        mStyleTextField = new JTextField(mStyleScrollPane.getViewport().getSize().width);
	mStyleList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent xEvent) {
		    int index = mStyleList.getSelectedIndex();
		    switch (mStyle[index]) {
		    case "Plain":
			mCurrentStyle = Font.PLAIN;
			break;
		    case "Italic":
			mCurrentStyle = Font.ITALIC;
			break;
		    case "Bold":
			mCurrentStyle = Font.BOLD;
			break;
		    case "Bold Italic":
			mCurrentStyle = Font.BOLD | Font.ITALIC;
			break;
		    default:
			break;
		    }
		    mStyleTextField.setText(mStyle[index]);
		    mLabel.setFont(new Font(mCurrentFont, 
					    mCurrentStyle,
					    mCurrentSize));
		}
	    });
        mStylePanel = new JPanel(new BorderLayout());
	mStylePanel.add(mStyleLabel, BorderLayout.NORTH);
	mStylePanel.add(mStyleTextField, BorderLayout.CENTER);
	mStylePanel.add(mStyleScrollPane, BorderLayout.SOUTH);
    }

    public void createTextColorPanel() {
	JLabel mTextColorLabel = new JLabel("Text Color");
	mTextColorList = new JList<>(mColorsText);
	JScrollPane mTextColorScrollPane = new JScrollPane(mTextColorList);
	mTextColorTextField = new JTextField(mTextColorScrollPane.getViewport().getSize().width);
	mTextColorList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent xEvent) {
		    int index = mTextColorList.getSelectedIndex();
		    mTextColorTextField.setText(mColorsText[index]);
		    mDefaultTextColor = mColors[index];
		    mLabel.setForeground(mColors[index]);
		}
	    });

	mTextColorPanel = new JPanel(new BorderLayout());
	mTextColorPanel.add(mTextColorLabel, BorderLayout.NORTH); 
	mTextColorPanel.add(mTextColorTextField, BorderLayout.CENTER);
	mTextColorPanel.add(mTextColorScrollPane, BorderLayout.SOUTH);
    }

        public void createBackgroundColorPanel() {
	JLabel mBackgroundColorLabel = new JLabel("Background Color");
	mBackgroundColorList = new JList<>(mColorsText);
	JScrollPane mBackgroundColorScrollPane = new JScrollPane(mBackgroundColorList);
	mBackgroundColorTextField = new JTextField(mBackgroundColorScrollPane.getViewport().getSize().width);
	mBackgroundColorList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent xEvent) {
		    int index = mBackgroundColorList.getSelectedIndex();
		    mBackgroundColorTextField.setText(mColorsText[index]);
		    mDefaultBackgroundColor = mColors[index];
		    mLabelPanel.setBackground(mColors[index]);
		}
	    });

	mBackgroundColorPanel = new JPanel(new BorderLayout());
	mBackgroundColorPanel.add(mBackgroundColorLabel, BorderLayout.NORTH); 
	mBackgroundColorPanel.add(mBackgroundColorTextField, BorderLayout.CENTER);
	mBackgroundColorPanel.add(mBackgroundColorScrollPane, BorderLayout.SOUTH);
    }
}
