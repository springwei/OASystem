package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;

import bean.Daly;
import bean.Date;
import bean.LeaveTable;
import bean.MoneyTable;
import bean.News;
import bean.Note;
import bean.Notice;
import bean.Sign;
import bean.User;


public class Data {
	private static String appId = "E4XM7ti8sp8X71HKhnTMA7";
    private static String appKey = "6YxioUrZtd59z5vkoonMA7";
    private static String masterSecret = "42LP2pRF9f5Rm9ak1l3dr5";
	static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	static String CID1 ="91ebd7f26b1b17d70886b1d8efd1228b";//联想
	static String CID2= "88cd31609db746fc65dc4298ab1a29bb";//note3
	public static NotificationTemplate notificationTemplateDemo() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle("您的报销申请已经通过");
        template.setText("OaSystem有新的消息");
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("s1");
        return template;
    }
	void refuse(){
		// 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo();
        ListMessage message = new ListMessage();
        template.setTitle("您的报销申请未通过");
        template.setText("OaSystem有新的消息");
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        Target target1 = new Target();
        Target target2 = new Target();
        target1.setAppId(appId);
     //   target1.setClientId(CID1);
   //     target1.setAlias(Alias1);
        target2.setAppId(appId);
        target2.setClientId(CID2);
   //     target2.setAlias(Alias2);
        targets.add(target1);
        targets.add(target2);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
	}
	void access(){
		// 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo();
        ListMessage message = new ListMessage();
       
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        Target target1 = new Target();
        Target target2 = new Target();
        target1.setAppId(appId);
     //   target1.setClientId(CID1);
   //     target1.setAlias(Alias1);
        target2.setAppId(appId);
        target2.setClientId(CID2);
   //     target2.setAlias(Alias2);
        targets.add(target1);
        targets.add(target2);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
	}
	void refuse1(){
		// 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo();
        ListMessage message = new ListMessage();
        
        template.setTitle("您的请假申请未通过");
        template.setText("OaSystem有新的消息");
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        Target target1 = new Target();
        Target target2 = new Target();
        target1.setAppId(appId);
     //   target1.setClientId(CID1);
   //     target1.setAlias(Alias1);
        target2.setAppId(appId);
        target2.setClientId(CID2);
   //     target2.setAlias(Alias2);
        targets.add(target1);
        targets.add(target2);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
	}
	void access1(){
		// 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo();
        ListMessage message = new ListMessage();
       
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        Target target1 = new Target();
        Target target2 = new Target();
        target1.setAppId(appId);
     //   target1.setClientId(CID1);
   //     target1.setAlias(Alias1);
        target2.setAppId(appId);
        target2.setClientId(CID2);
   //     target2.setAlias(Alias2);
        targets.add(target1);
        targets.add(target2);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
	}


	Statement stmt;
	Connection conn;
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost/oasystem?useUnicode=true&characterEncoding=GBK",
							"root", "123456");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


//登录
	public User login(String phone, String password) {
		String loginStr = "select * from user where phone = " + phone
				+ " and password = " + password;
		try {
			ResultSet res = stmt.executeQuery(loginStr);
			while (res.next()) {
				int id = res.getInt("userid");
				String role=res.getString("role");
				
				User user=new User();
				user.setInfo("success");
				user.setId(id);
				user.setRole(role);
				user.setName(res.getString("name"));
				user.setDepartment(res.getString("department"));
				return user;
			}
			User user=new User();
			user.setInfo("fail");
			res.close();
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<LeaveTable> getLeaveTable(String department,String status) {
		// TODO Auto-generated method stub
		String loginStr = "select * from leave_i where department = '" + department+"' and status='"+status+"' order by create_time desc ";
		try {
			ResultSet res = stmt.executeQuery(loginStr);
			List<LeaveTable> leaveTables=new ArrayList<LeaveTable>();
			while (res.next()) {
				LeaveTable leaveTable=new LeaveTable();
				leaveTable.setId(res.getInt("id"));
				leaveTable.setTopic(res.getString("topic"));
				leaveTable.setContent(res.getString("content"));
				leaveTable.setBeginTime(res.getString("begin_time"));
				leaveTable.setEndTime(res.getString("end_time"));
				leaveTable.setCreateTime(res.getString("create_time"));
				leaveTable.setDepartment(res.getString("department"));
				leaveTable.setUserId(res.getInt("user_id")+"");
				/*User user=getUser(res.getInt("user_id"));
				leaveTable.setUser(user);*/
				leaveTables.add(leaveTable);
			}
			return leaveTables;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<MoneyTable> getMoneyTable(String department,String status) {
		// TODO Auto-generated method stub
		String loginStr = "select * from money where department = '" + department+"' and status='"+status+"'order by create_time desc";
		try {
			ResultSet res = stmt.executeQuery(loginStr);
			List<MoneyTable> moneytables=new ArrayList<MoneyTable>();
			while (res.next()) {
				MoneyTable moneytable=new MoneyTable();
				moneytable.setId(res.getInt("id"));
				moneytable.setTopic(res.getString("topic"));
				moneytable.setContent(res.getString("content"));
				moneytable.setCreateTime(res.getString("create_time"));
				moneytable.setDepartment(res.getString("department"));
				moneytable.setUserId(res.getInt("user_id")+"");
				/*User user=getUser(res.getInt("user_id"));
				leaveTable.setUser(user);*/
				moneytables.add(moneytable);
			}
			return moneytables;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public User getUser(int userId){
		String loginStr = "select * from user where userid = " + userId;
		try {
			ResultSet res = stmt.executeQuery(loginStr);
			while (res.next()) {
				int id = res.getInt("userid");
				String role=res.getString("role");
				User user=new User();
				user.setPhone(res.getString("phone"));
				user.setId(id);
				user.setName(res.getString("name"));
				user.setRole(role);
				user.setDepartment(res.getString("department"));
				return user;
			}
			
			return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
//公告
	public List<Notice> getNotice(){
		String noticeStr = "select * from notice order by time desc";
		try {
			ResultSet res = stmt.executeQuery(noticeStr);
			List<Notice> notices=new ArrayList<Notice>();
			while (res.next()) {
				int id = res.getInt("id");
				String issuer=res.getString("issuer");
				String content=res.getString("content");
				String title=res.getString("title");
				String time=res.getString("time");
				Notice notice=new Notice();
				notice.setId(id);
				notice.setIssuer(issuer);
				notice.setContent(content);
				notice.setTitle(title);
				notice.setTime(time);
				notices.add(notice);
			}
			
			res.close();
			return notices;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//新闻
	public List<News> getNews(){
		String newsStr = "select * from news order by time desc";
		try {
			ResultSet res = stmt.executeQuery(newsStr);
			List<News> newss=new ArrayList<News>();
			while (res.next()) {
				int id = res.getInt("id");
				String issuer=res.getString("issuer");
				String content=res.getString("content");
				String title=res.getString("title");
				String time=res.getString("time");
				News news=new News();
				news.setId(id);
				news.setIssuer(issuer);
				news.setContent(content);
				news.setTitle(title);
				news.setTime(time);
				newss.add(news);
			}
			
			res.close();
			return newss;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean createLeave(int userId, String topic, String content,
			String startTime, String endTime,String department) {
		//添加请假表
		// TODO Auto-generated method stub
		String createLeave="insert into leave_i (user_id,topic,content,begin_time,end_time,create_time,status,department) values("+userId+",'"+topic+"','"+content+"','"+startTime+"','"+endTime+"','"+getTime()+"','wait','"+department+"')";

		
		try {
			
			stmt.execute(createLeave);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean createMoney(int userId, String topic, String content,
			String department) {
		//添加请假表
		// TODO Auto-generated method stub
		String createMoney="insert into money (user_id,topic,content,create_time,status,department) values("+userId+",'"+topic+"','"+content+"','"+getTime()+"','wait','"+department+"')";

		
		try {
			
			stmt.execute(createMoney);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

//获取当前时间
	public String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new java.util.Date());
	}

	public void closeSql() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//更新请假状态
	public boolean updataLeave(String leaveid, String status) {
		// TODO Auto-generated method stub
		boolean result=false;
		String updataText="update leave_i set status='"+status+"' where id="+leaveid;
		if(status.equals("refuse")){
			refuse1();
			
		}else if(status.equals("access")){
			access1();
		}
		try {
			stmt.execute(updataText);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean updataMoney(String moneyid, String status) {
		// TODO Auto-generated method stub
		boolean result=false;
		String updataText="update money set status='"+status+"' where id="+moneyid;
		if(status.equals("refuse")){
			refuse();
			
		}else if(status.equals("access")){
			access();
		}
		
		try {
			stmt.execute(updataText);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public boolean addNews(String topic, String content, String name) {
		// TODO Auto-generated method stub
		
		boolean result=false;
		String sqlText="insert into news(issuer,time,content,title) values ('"+name+"','"+getTime()+"','"+content+"','"+topic+"')";
		try {
			stmt.execute(sqlText);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//发布公告
	public boolean addNotices(String topic, String content, String name) {
		// TODO Auto-generated method stub
		
		boolean result=false;
		String sqlText="insert into notice(issuer,time,content,title) values ('"+name+"','"+getTime()+"','"+content+"','"+topic+"')";
		try {
			stmt.execute(sqlText);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	public List<User> getUsers() {
		// TODO Auto-generated method stub
		String sqlText="select * from user";
		List<User> users=new ArrayList<User>();
		try {
			ResultSet res = stmt.executeQuery(sqlText);
			while(res.next()){
				User user=new User();
				user.setId(res.getInt("userid"));
				user.setName(res.getString("name"));
				user.setPhone(res.getString("phone"));
				user.setRole(res.getString("role"));
				user.setPassword(res.getString("password"));
				user.setDepartment(res.getString("department"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	public boolean updateUser(String id, String name, String department,
			String phone, String password, String role) {
		
		boolean result=false;
		String sqlText="update user set name='"+name+"',department='"+department+"',phone='"+phone+"',password='"+password+"',role='"+role+"' where userid="+id+"";
		try {
			stmt.execute(sqlText);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	
	}


	public boolean addUser(String name, String password, String phone,
			String department, String role) {
		// TODO Auto-generated method stub
		
		boolean result=false;
		String addUserSql="insert into user (name,password,phone,department,role) values('"+name+"','"+password+"','"+phone+"','"+department+"','"+role+"')";
		try {
			stmt.execute(addUserSql);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public boolean addsign(String id, String address, String department) {
		// TODO Auto-generated method stub
		boolean result=false;
		String addSql="insert into sign(user_id,address,department,time) values('"+id+"','"+address+"','"+department+"','"+getTime()+"')";
		try {
			stmt.execute(addSql);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public List<Sign> getSign(String department) {
		// TODO Auto-generated method stub
		String sql="select * from sign where department='"+department+"'";
		List<Sign> signTables=new ArrayList<Sign>();
		try {
			ResultSet res = stmt.executeQuery(sql);
			while(res.next()){
				Sign sign=new Sign();
				sign.setId(res.getInt("id"));
				sign.setUser_id(res.getString("user_id"));
				sign.setAddress(res.getString("address"));
				sign.setDepartment(res.getString("department"));
				sign.setTime(res.getString("time"));
				signTables.add(sign);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signTables;
	}


	public boolean createNote(String userId, String content) {
		// TODO Auto-generated method stub
		boolean result=false;
		String sql="insert into note (user_id,content,create_time,change_time) values ("+userId+",\""+content+"\",\""+getTime()+"\",\""+getTime()+"\")";
		try {
			stmt.execute(sql);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public boolean changeNote(int noteId, String content) {
		// TODO Auto-generated method stub
		boolean result=false;
		String sql="update note set content=\""+content+"\",change_time=\""+getTime()+"\" where id="+noteId;
		try {
			stmt.execute(sql);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public List<Note> getNote(String userId) {
		
		// TODO Auto-generated method stub
		List<Note> notes=new ArrayList<Note>();
		String sql="select * from note where user_id="+userId;
		try {
			ResultSet res = stmt.executeQuery(sql);
			while(res.next()){
				Note note=new Note();
				note.setId(res.getInt("id"));
				note.setUserId(res.getInt("user_id"));
				note.setContent(res.getString("content"));
				note.setCreateTime(res.getString("create_time"));
				note.setChangeTime(res.getString("change_time"));
				notes.add(note);
			}
			return notes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public boolean deleteNote(String noteId) {
		// TODO Auto-generated method stub
		boolean result=false;
		String sql="delete from note where id="+noteId;
		try {
			stmt.execute(sql);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public List<Daly> getDaly(String id) {
		// TODO Auto-generated method stub
		String sql="select * from daly where user_id="+id;
		List<Daly> dalys=new ArrayList<Daly>();
		try {
			ResultSet res=stmt.executeQuery(sql);
		
			while(res.next()){
				Daly daly=new Daly();
				daly.setId(res.getInt("id"));
				daly.setUserId(res.getInt("user_id"));
				daly.setTopic(res.getString("topic"));
				daly.setContent(res.getString("content"));
				daly.setTime(res.getString("time"));
				dalys.add(daly);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dalys;
	}


	public boolean addDaly(String id, String topic, String content, String time) {
		// TODO Auto-generated method stub
		String sql="insert into daly(user_id,time,topic,content) values("+id+",\""+time+"\",\""+topic+"\",\""+content+"\")";
		
		boolean result=false;
		try {
			stmt.execute(sql);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public List<Date> getDate(String id) {
		// TODO Auto-generated method stub
		String sql="select * from date where user_id="+id;
		List<Date> dates=new ArrayList<Date>();
		try {
			ResultSet res=stmt.executeQuery(sql);
		
			while(res.next()){
				Date date=new Date();
				date.setId(res.getInt("id"));
				date.setUserId(res.getInt("user_id"));
				date.setTopic(res.getString("topic"));
				date.setContent(res.getString("content"));
				date.setTime(res.getString("time"));
				dates.add(date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dates;
	}
//增加日志
	public boolean addDate(String id, String topic, String content, String time) {
		// TODO Auto-generated method stub
		String sql="insert into date(user_id,time,topic,content) values("+id+",\""+time+"\",\""+topic+"\",\""+content+"\")";
		
		boolean result=false;
		try {
			stmt.execute(sql);
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}	
}
