package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class ConstructionMaterial extends SugarRecord<ConstructionMaterial>{
	private long cm_id;
	private String Materials;
	private String Materials_am;
	
    public boolean isNew() {
        long count = Select.from(ConstructionMaterial.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getCm_id() {
		return cm_id;
	}

	public void setCm_id(long cm_id) {
		this.cm_id = cm_id;
	}

	public String getMaterials() {
		return Materials;
	}

	public void setMaterials(String materials) {
		Materials = materials;
	}

	public String getMaterials_am() {
		return Materials_am;
	}

	public void setMaterials_am(String materials_am) {
		Materials_am = materials_am;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
