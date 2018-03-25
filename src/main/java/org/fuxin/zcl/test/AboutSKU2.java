package org.fuxin.zcl.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutSKU2 {
	
	private Map<String,Integer> skuMap2 = new HashMap<>();
	
	public AboutSKU2(){
		skuMap2.put("goods_a", 6);
//		skuMap.put("goods_b", 3);
//		skuMap.put("goods_c", 4);
	}
	
	private Map<String,Integer> initMap(){
		Map<String,Integer> result = new HashMap<>();
		result.put("goods_a", 6);
		return result;
	}
	
	
	public static void main(String[] args) {
		AboutSKU2 ask = new AboutSKU2();
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
//			Map<String,Integer> skuMap = skuMap2;
			Map<String,Integer> skuMap = initMap();
			synchronized (skuMap) {
				System.out.println(Thread.currentThread().getName()+" is getLock....."+System.currentTimeMillis());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					for(String sku:skus){
						if(skuMap.containsKey(sku)){
							if(skuMap.get(sku)<=0){
								System.out.println(Thread.currentThread().getName()+" "+sku+" 已经卖完了");
								return false;
							}
							System.out.println(Thread.currentThread().getName()+" "+"卖出 "+sku+" 一件");
							
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
