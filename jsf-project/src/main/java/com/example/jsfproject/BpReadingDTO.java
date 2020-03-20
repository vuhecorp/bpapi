/*  
 * ===========================================================================  
 *                     Proprietary Rights Notice  
 * ===========================================================================  
 * All rights reserved.  This document contains valuable and proprietary  
 * properties of Hersa Corp.  This document embodies substantial  
 * creative works and confidential information, ideas and expressions, no  
 * part of which may be reproduced or transmitted IN ANY FORM OR BY ANY MEANS,  
 * electronic, mechanical or otherwise, including but not limited to photo-  
 * copying and recording or in connection with any information storage or  
 * retrieval system without the express written permission of Easy Access Inc.  
 * ===========================================================================  
 */  
package com.example.jsfproject;

import java.io.Serializable;
import java.math.BigDecimal;
public class BpReadingDTO implements Serializable {

private static final long serialVersionUID = 1L;
private java.util.Date _Date;

private String _Description;

private int _Diastolic;

private long _Id;

private int _Pulse;

private int _Systolic;

private String _Tags;

private BigDecimal _Weight;



public java.util.Date getDate() { 
  return _Date; 
}
public void setDate(java.util.Date value) { 
  _Date = value; 
}

public String getDescription() { 
  return _Description; 
}
public void setDescription(String value) { 
  _Description = value; 
}

public int getDiastolic() { 
  return _Diastolic; 
}
public void setDiastolic(int value) { 
  _Diastolic = value; 
}

public long getId() { 
  return _Id; 
}
public void setId(long value) { 
  _Id = value; 
}

public int getPulse() { 
  return _Pulse; 
}
public void setPulse(int value) { 
  _Pulse = value; 
}

public int getSystolic() { 
  return _Systolic; 
}
public void setSystolic(int value) { 
  _Systolic = value; 
}

public String getTags() { 
  return _Tags; 
}
public void setTags(String value) { 
  _Tags = value; 
}

public BigDecimal getWeight() { 
  return _Weight; 
}
public void setWeight(BigDecimal value) { 
  _Weight = value; 
}

public String getInfo() {
 StringBuffer buf = new StringBuffer();
  buf.append("Date=" + _Date + "| "); 
  buf.append("Description=" + _Description + "| "); 
  buf.append("Diastolic=" + _Diastolic + "| "); 
  buf.append("Id=" + _Id + "| "); 
  buf.append("Pulse=" + _Pulse + "| "); 
  buf.append("Systolic=" + _Systolic + "| "); 
  buf.append("Tags=" + _Tags + "| "); 
  buf.append("Weight=" + _Weight + "| "); 
  return buf.toString();  }}
/***************** end *********************/ 
