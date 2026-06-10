package com;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import javax.swing.SwingConstants;
import java.awt.Color;


public class DrinkSystem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public JComboBox<String> cmbDrink;
    public JRadioButton rbRegularSugar, rbHalfSugar, rbSugarFree;
    public JRadioButton rbRegularIce, rbEasyIce, rbIceFree;
    public JSpinner spinnerQty;
    public JButton btnAdd, btnCheckout, btnClear;
    public JButton btnPrint, btnExit; 
    public JTextArea txtOrderList;
    public JLabel lblTotal;
    
private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//DrinkSystem frame = new DrinkSystem();

                    OrderModel model = new OrderModel();
                    DrinkSystem view = new DrinkSystem(); // 自身即為 View
                    new DrinkController(model, view);

					//frame.setVisible(true);
                    view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrinkSystem() {
		setBackground(new Color(240, 240, 240));
		setTitle("飲料點餐系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("飲料點餐系統");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		lblNewLabel.setBounds(190, 10, 120, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("選擇飲料：");
		lblNewLabel_1.setBounds(30, 60, 70, 15);
		contentPane.add(lblNewLabel_1);
		
        String[] drinks = {"美式咖啡 ($45)", "莊園級拿鐵 ($75)", "焦糖瑪奇朵 ($85)", "紅茶 ($30)", "綠茶 ($30)"};
		cmbDrink = new JComboBox<>(drinks);
		cmbDrink.setBounds(100, 56, 130, 23);
		contentPane.add(cmbDrink);
		
		JLabel lblNewLabel_2 = new JLabel("甜度：");
		lblNewLabel_2.setBounds(30, 100, 50, 15);
		contentPane.add(lblNewLabel_2);
		
		rbRegularSugar = new JRadioButton("正常糖");
		rbRegularSugar.setSelected(true);
		buttonGroup.add(rbRegularSugar);
		rbRegularSugar.setBounds(100, 96, 70, 23);
		contentPane.add(rbRegularSugar);
		
		rbHalfSugar = new JRadioButton("半糖");
		buttonGroup.add(rbHalfSugar);
		rbHalfSugar.setBounds(175, 96, 60, 23);
		contentPane.add(rbHalfSugar);
		
		rbSugarFree = new JRadioButton("無糖");
		buttonGroup.add(rbSugarFree);
		rbSugarFree.setBounds(240, 96, 90, 23);
		contentPane.add(rbSugarFree);
		
		JLabel lblNewLabel_2_1 = new JLabel("冰塊：");
		lblNewLabel_2_1.setBounds(30, 140, 50, 15);
		contentPane.add(lblNewLabel_2_1);
		
		rbRegularIce = new JRadioButton("正常冰");
		rbRegularIce.setSelected(true);
		buttonGroup_1.add(rbRegularIce);
		rbRegularIce.setBounds(100, 136, 70, 23);
		contentPane.add(rbRegularIce);
		
		rbEasyIce = new JRadioButton("少冰");
		buttonGroup_1.add(rbEasyIce);
		rbEasyIce.setBounds(175, 136, 60, 23);
		contentPane.add(rbEasyIce);
		
		rbIceFree = new JRadioButton("去冰");
		buttonGroup_1.add(rbIceFree);
		rbIceFree.setBounds(240, 136, 60, 23);
		contentPane.add(rbIceFree);
		
		JLabel lblQty = new JLabel("數量：");
		lblQty.setBounds(30, 180, 50, 15);
		contentPane.add(lblQty);
		
		spinnerQty = new JSpinner();
		spinnerQty.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerQty.setBounds(100, 177, 60, 22);
		contentPane.add(spinnerQty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 220, 440, 130);
		contentPane.add(scrollPane);
		
		txtOrderList = new JTextArea();
		txtOrderList.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
		txtOrderList.setEditable(false);
        scrollPane.setViewportView(txtOrderList);
		
		lblTotal = new JLabel("總金額： $0");
		lblTotal.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblTotal.setBounds(30, 375, 150, 20);
		contentPane.add(lblTotal);

		btnAdd = new JButton("加入購物車");

//		btnAdd.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});

		btnAdd.setBounds(340, 56, 120, 23);
		contentPane.add(btnAdd);
		
		btnClear = new JButton("清空清除");

//		btnClear.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});

		btnClear.setBounds(340, 96, 120, 23);
		contentPane.add(btnClear);
		
		btnPrint = new JButton("列印明細");

//		btnPrint.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});

		btnPrint.setBounds(340, 136, 120, 23);
		contentPane.add(btnPrint);
		
		btnCheckout = new JButton("確認結帳");

//		btnCheckout.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});

		btnCheckout.setBounds(220, 375, 110, 25);
		contentPane.add(btnCheckout);
		
		btnExit = new JButton("關閉程式");

//		btnExit.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});

		btnExit.setBounds(360, 375, 110, 25);
		contentPane.add(btnExit);

		
	}
}

//=========================================================================
//3. Model 層 (負責處理購物車與訂單資料邏輯)
//=========================================================================
class OrderModel {
 
 public static class OrderItem {
     public String drinkName;
     public int price;
     public int quantity;
     public String sweetness;
     public String ice;

     public OrderItem(String drinkName, int price, int quantity, String sweetness, String ice) {
         this.drinkName = drinkName;
         this.price = price;
         this.quantity = quantity;
         this.sweetness = sweetness;
         this.ice = ice;
     }

     public int getSubtotal() {
         return this.price * this.quantity;
     }

     @Override
     public String toString() {
         return String.format("%s (%s/%s) x%d - $%d", 
                 drinkName, sweetness, ice, quantity, getSubtotal());
     }
 }

 private List<OrderItem> cart;

 public OrderModel() {
     this.cart = new ArrayList<>();
 }

 public void addToCart(OrderItem item) {
     cart.add(item);
 }

 public List<OrderItem> getCart() {
     return cart;
 }

 public int calculateTotal() {
     int total = 0;
     for (OrderItem item : cart) {
         total += item.getSubtotal();
     }
     return total;
 }

 public void clearCart() {
     cart.clear();
 }
}

//=========================================================================
//4. Controller 層 (監聽與處理按鈕動作、實作指定的純淨樣式列印)
//=========================================================================
class DrinkController {
 private OrderModel model;
 private DrinkSystem view; // 改為綁定主類別 DrinkSystem

 public DrinkController(OrderModel model, DrinkSystem view) {
     this.model = model;
     this.view = view;
     initController();
 }

//************************event************************/
private void initController() {
     // 1. 加入購物車
	view.btnAdd.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

//	 view.btnAdd.addMouseListener(new MouseAdapter() {
//		@Override
//		public void mouseClicked(MouseEvent e) {
        	 
        	 	String selectedItem = (String) view.cmbDrink.getSelectedItem();
             String drinkName = "";
             int price = 0;
             
             if (selectedItem.contains("美式咖啡")) {
                 drinkName = "美式咖啡"; price = 45;
             } else if (selectedItem.contains("莊園級拿鐵")) {
                 drinkName = "莊園級拿鐵"; price = 75;
             } else if (selectedItem.contains("焦糖瑪奇朵")) {
                 drinkName = "焦糖瑪奇朵"; price = 85;
             } else if (selectedItem.contains("紅茶")) { 
                 drinkName = "紅茶"; price = 30; 
             } else if (selectedItem.contains("綠茶")) { 
                 drinkName = "綠茶"; price = 30; 
             } else {
                 drinkName = selectedItem; price = 0;
             }

             String sweetness = "正常糖";
             if (view.rbHalfSugar.isSelected()) sweetness = "半糖";
             else if (view.rbSugarFree.isSelected()) sweetness = "無糖";

             String ice = "正常冰";
             if (view.rbEasyIce.isSelected()) ice = "少冰";
             else if (view.rbIceFree.isSelected()) ice = "去冰";

             int quantity = (int) view.spinnerQty.getValue();

             OrderModel.OrderItem item = new OrderModel.OrderItem(drinkName, price, quantity, sweetness, ice);
             model.addToCart(item);
             updateView();
             System.out.println("加入購物車");
         }
     });

     // 2. 清空清除
	view.btnClear.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

//	view.btnClear.addMouseListener(new MouseAdapter() {
//		 	@Override
//		 	public void mouseClicked(MouseEvent e) {
    	 
             model.clearCart();
             updateView();
//             view.txtOrderList.setText(""); //test
             System.out.println("清空清除");
        }
     });

     // 3. 確認結帳
	view.btnCheckout.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

//	view.btnCheckout.addMouseListener(new MouseAdapter() {
//		@Override
//		public void mouseClicked(MouseEvent e) {
        	 
        	 	if (model.getCart().isEmpty()) {
                 JOptionPane.showMessageDialog(view, "購物車是空的喔！", "提示", JOptionPane.WARNING_MESSAGE);
             } else {
                 JOptionPane.showMessageDialog(view, "訂單已送出！總計金額: NT$" + model.calculateTotal() + "元\n謝謝惠顧！", "結帳成功", JOptionPane.INFORMATION_MESSAGE);
                 model.clearCart();
                 updateView();
                 System.out.println("確認結帳");
 
             }
         }
     });

     // 4. 列印功能
	view.btnPrint.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

//	view.btnPrint.addMouseListener(new MouseAdapter() {
//		@Override
//		public void mouseClicked(MouseEvent e) {

			try {
				view.txtOrderList.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
     });

     // 5. 關閉程式
	view.btnExit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("關閉程式");
			System.exit(0);
         }
     });
 }

 private void updateView() {
     StringBuilder sb = new StringBuilder();
     for (OrderModel.OrderItem item : model.getCart()) {
         sb.append(item.toString()).append("\n");
     }
     view.txtOrderList.setText(sb.toString());
     view.lblTotal.setText("總金額： $" + model.calculateTotal());
 }
}




