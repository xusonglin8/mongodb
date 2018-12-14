package cn.com.taiji.mongoweb.controller;

import cn.com.taiji.mongoweb.model.User;
import cn.com.taiji.mongoweb.service.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 入口
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/get/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		userService.remove(id);
		return "delete sucess";
	}

	@RequestMapping("/add")
	public String insert() {
		User user = new User(16, "" + 16, 16);
		userService.insert(user);
		return "sucess";
	}

	@RequestMapping("/insert")
	public String insertAll() {
		List<User> list = new ArrayList<>();
		for (int i = 10; i < 15; i++) {
			list.add(new User(i, "" + i, i));
		}
		userService.insertAll(list);
		return "sucess";
	}

	@RequestMapping("/find/all")
	public List<User> find() {
		return userService.findAll();
	}

	@RequestMapping("/find/{start}")
	public List<User> findByPage(@PathVariable int start, User user) {
		Pageable pageable = new PageRequest(start, 2);
		return userService.findByPage(user, pageable);
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id) {
		User user = new User(id, "" + 1, 1);
		userService.update(user);
		return "sucess";
	}
//   near附近的建筑
	@RequestMapping("/geo")
	public List<DBObject> geo() {

		DBObject query = new BasicDBObject();
		Point point = new Point(118.783799, 31.979234);
		// point.setLng(118.783799);
		// point.setLat(31.979234);
		int limit = 5;
		Long maxDistance = 5000L; // 米
		List<DBObject> list = userService.geo("geoNear", query, point, limit, maxDistance);
		for (DBObject obj : list)
			System.out.println(obj);
		return list;
	}
  // 多边形
	@RequestMapping("/withinPolygon")
	public List<DBObject> withinPolygon(){
		DBObject query = new BasicDBObject();
		int limit = 5;
	    DBObject fields = new BasicDBObject();
	    List<double[]> polygon =new ArrayList<double[]>();
	   polygon.add(new double[]{121.46326251,31.22373576}); 
     polygon.add(new double[]{121.46397061,31.21879961}); 
     polygon.add(new double[]{121.47126622,31.22188244}); 
     polygon.add(new double[]{121.46748967,31.22329537}); 
     polygon.add(new double[]{121.46326251,31.22373576}); 
		List<DBObject> list = userService.withinPolygon("geoNear","loc",polygon , fields, query , limit);
		for (DBObject obj : list)
			System.out.println(obj);
		return list;
	}
	// 附近的建筑+多边形
		@RequestMapping("/withinPolygons")
		public List<DBObject> withinPolygons(){
			List<DBObject> lists=new ArrayList<DBObject>();
			DBObject query = new BasicDBObject();
			int limit = 5;
		    DBObject fields = new BasicDBObject();
		    List<double[]> polygon =new ArrayList<double[]>();
		 polygon.add(new double[]{118.783799,31.979234}); 
	     polygon.add(new double[]{118.639523,32.070078}); 
	     polygon.add(new double[]{118.803032,32.09248}); 
	     polygon.add(new double[]{118.790611,32.047616}); 
	     polygon.add(new double[]{118.783799,31.979234}); 
		 List<DBObject> list1 = userService.withinPolygon("geoNear","loc",polygon , fields, query , limit);
			for (DBObject obj : list1) {
				lists.add(obj);
				System.out.println(obj);}
			Point point = new Point(118.783799, 31.979234);
			// point.setLng(118.783799);
			// point.setLat(31.979234);
			int limit2 = 5;
			Long maxDistance = 5000L; // 米
			List<DBObject> list2 = userService.geo("geoNear", query, point, limit2, maxDistance);
			for (DBObject obj : list2) {
				lists.add(obj);
				System.out.println(obj);
			}
			List<DBObject> listNew=new ArrayList<DBObject>();
	        for (DBObject obj:lists) {
	            if(!listNew.contains(obj)){
	                listNew.add(obj);
	            }
	        }
	        for (DBObject obj : listNew) {
				System.out.println(obj);
			}
	        return listNew;
		}
	
	
	/*@RequestMapping("/geo")
	public List<DBObject> geo(){
		DBObject query = new BasicDBObject();
		Point point = new Point(118.783799,31.979234);
//		point.setLng(118.783799);
//		point.setLat(31.979234);
		int limit = 5;
		List<double[]> list = new ArrayList<>();
		list.add(new double[]{118.783799,31.979234});
		list.add(new double[]{118.783799,31.979234});
		list.add(new double[]{118.783799,31.979234});
		list.add(new double[]{118.783799,31.979234});
		Long maxDistance = 5000L; // 米
		List<DBObject> listfinal = userService.withinPolygon("point.test", String locationField,
				list, DBObject fields, query, 5);//("point.test", query, point, limit, maxDistance);
		for(DBObject obj : listfinal)
			System.out.println(obj);
		return listfinal;
	}*/

}
