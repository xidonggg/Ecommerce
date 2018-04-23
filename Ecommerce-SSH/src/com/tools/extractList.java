package com.tools;

import java.util.ArrayList;
import java.util.List;

import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.Order;
import com.po.User;

public class extractList {
	//T t;
	public static List<User> getExtractlistUser (List<User> list1,List<User> list2)
	{
		List<User> result = new ArrayList<User>();
		/*for(User t : list2){
			if(list1.contains(t)){
				result.add(t);
			}
		}*/
		if(list1 == null)
			return list2;
		else if(list2 == null)
			return list1;
		for(int i = 0; i < list1.size();i++)
		{
			for(int j = 0; j < list2.size(); j++)
			{
				if(list1.get(i).getId() == list2.get(j).getId())
					result.add(list1.get(i));
			}
		}
		return result;
		
	}
	public static List<Commodity> getExtractlistCommodity (List<Commodity> list1,List<Commodity> list2)
	{
		List<Commodity> result = new ArrayList<Commodity>();
		if(list1 == null)
			return list2;
		else if(list2 == null)
			return list1;
		for(int i = 0; i < list1.size();i++)
		{
			for(int j = 0; j < list2.size(); j++)
			{
				if(list1.get(i).getId() == list2.get(j).getId())
					result.add(list1.get(i));
			}
		}
		return result;
		
	}
	public static List<Order> getExtractlistOrder (List<Order> list1,List<Order> list2)
	{
		List<Order> result = new ArrayList<Order>();
		if(list1 == null)
			return list2;
		else if(list2 == null)
			return list1;
		for(int i = 0; i < list1.size();i++)
		{
			for(int j = 0; j < list2.size(); j++)
			{
				if(list1.get(i).getId() == list2.get(j).getId())
					result.add(list1.get(i));
			}
		}
		return result;
		
	}
	public static List<LeaveMessage>getExtractlistLeaveMessage(List<LeaveMessage>list1,List<LeaveMessage>list2)
	{
		List<LeaveMessage> result = new ArrayList<LeaveMessage>();
		if(list1 == null)
			return list2;
		else if(list2 == null)
			return list1;
		for(int i = 0; i < list1.size();i++)
		{
			for(int j = 0; j < list2.size(); j++)
			{
				if(list1.get(i).getId() == list2.get(j).getId())
					result.add(list1.get(i));
			}
		}
		return result;
	}
}
