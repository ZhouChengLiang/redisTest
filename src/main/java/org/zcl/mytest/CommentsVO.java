package org.zcl.mytest;

public class CommentsVO extends Comments{
	
    private String thirdChannel;

	public String getThirdChannel() {
		return thirdChannel;
	}

	public void setThirdChannel(String thirdChannel) {
		this.thirdChannel = thirdChannel;
	}

	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length()-1)+" , thirdChannel = "+thirdChannel+"]";
	}
    
}
