package com.thyb.weightservice;

import com.thyb.weightservice.Weight;
 interface IWeight {
    Weight getWeight() ;
    boolean setTare(); 
    boolean setZero(); 
    void doNumberTare(int numbertare);
    boolean reset(); 
}
