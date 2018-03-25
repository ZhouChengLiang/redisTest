package org.fuxin.zcl.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutSKU {
	
	private Map<String,Integer> skuMap = new HashMap<>();
	
	public AboutSKU(){
		skuMap.put("goods_a", 6);
//		skuMap.put("goods_b", 3);
//		skuMap.put("goods_c", 4);
	}
	
	public static void main(String[] args) {
		AboutSKU ask = new AboutSKU();
		List<String> skus = Arrays.asList("goods_a","goods_b");
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
		new Thread(()->ask.limit(skus)).start();
	}
	
	
	public boolean limit(List<String> skus){
		if(null != skus && !skus.isEmpty()){
			System.out.println(Thread.currentThread().getName()+" is waiting....."+System.currentTimeMillis());
			synchronized (skuMap) {
				
				System.out.println(Thread.currentThread().getName()+" is getLock.....");
				try {
					for(String sku:skus){
						if(skuMap.containsKey(sku)){
							if(skuMap.get(sku)<=0){
								System.out.println(Thread.currentThread().getName()+" "+sku+" 已经卖完了");
								return false;
							}
							System.out.println(Thread.currentThread().getName()+" "+"卖出 "+sku+" 一件");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							skuMap.put(sku, skuMap.get(sku)-1);
						}
					}
				} finally {
					
				}
			}
		}
		return true;
	}
}
