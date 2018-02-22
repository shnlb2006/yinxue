package com.yinxue.computer.constant;

public enum Files {
	
	A("G://书籍"),
	A1("G://书籍//1.哲学"),
	A2("G://书籍//2.文学"),
	A3("G://书籍//3.计算机"),
	A4("G://书籍//4.其它"),
	B("G://照片"),
	C("G://影视"),
	D("G://音乐"),
	E("G://考试"),
	E1("G://考试//1.求职面试"),
	E2("G://考试//2.职业证书"),
	E3("G://考试//3.学历考试"),
	E4("G://考试//4.其它"),
	F("G://摘要"),
	F1("G://摘要//1.文本"),
	F2("G://摘要//2.图片"),
	F3("G://摘要//3.其它"),
	
	H("F://svn"),
	I("F://sourceCode"),
	J("F://develop"),
	J1("F://develop//1.workspace"),
	J2("F://develop//2.checkout"),
	J3("F://develop//3.output"),
	K("F://software"),
	K1("F://software//1.开发工具"),
	K2("F://software//2.应用软件"),
	K3("F://software//3.系统及office"),
	K4("F://software//4.UI工具"),
	K5("F://software//5.其它");
	
	private String path;
	
	Files(String path){
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}
