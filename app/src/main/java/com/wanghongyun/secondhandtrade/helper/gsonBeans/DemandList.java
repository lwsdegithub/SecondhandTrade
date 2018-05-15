package com.wanghongyun.secondhandtrade.helper.gsonBeans;

import java.util.List;

/**
 * 从服务端接受的Gson实体类
 */
public class DemandList {
	
	public List<DemandDetails> demandDetailsList;
	
	public static class DemandDetails{
		public int demandId;
		public int userId;
		public String userHeadIcon;
		public String userName;
		public String content;
		public String time;
		
		public DemandDetails(int demandId, int userId, String userHeadIcon, String userName, String content,
				String time) {
			super();
			this.demandId = demandId;
			this.userId = userId;
			this.userHeadIcon = userHeadIcon;
			this.userName = userName;
			this.content = content;
			this.time = time;
		}
		public int getDemandId() {
			return demandId;
		}
		public void setDemandId(int demandId) {
			this.demandId = demandId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserHeadIcon() {
			return userHeadIcon;
		}
		public void setUserHeadIcon(String userHeadIcon) {
			this.userHeadIcon = userHeadIcon;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
	}
}
