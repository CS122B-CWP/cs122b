package miniEbay.object;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DetailItem extends BriefItem {
	private String start_time;
	private String end_time;
	private String des;
	private String seller_id;
	private String status;
	private int bids;
	private List<String> photos;
	private List<ItemComment> commnets;

	public DetailItem() {
		photos = new ArrayList<String>();
		commnets = new ArrayList<ItemComment>();
	}

	@Override
	public JSONObject toJson() {
		JSONObject jsonStr = super.toJson();
		jsonStr.put("StartTime", this.start_time);
		jsonStr.put("EndTime", this.end_time);
		jsonStr.put("ConditionDescription", this.des);
		jsonStr.put("Seller_Id", this.seller_id);
		jsonStr.put("Status", this.status);
		jsonStr.put("Bids", this.bids);
		JSONArray photo_array = new JSONArray();
		for (String photo : photos)
			photo_array.put(photo);
		jsonStr.put("PictureURL", photo_array);

		JSONArray comment_array = new JSONArray();
		for (ItemComment commment : commnets)
			comment_array.put(commment.toJson());
		jsonStr.put("Comments", comment_array);
		
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBids() {
		return bids;
	}

	public void setBids(int bids) {
		this.bids = bids;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public List<ItemComment> getCommnets() {
		return commnets;
	}

	public void setCommnets(List<ItemComment> commnets) {
		this.commnets = commnets;
	}
}
