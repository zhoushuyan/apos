package com.posin.secondarydisplay.client;

interface ISecondaryDisplayCallback {
	void onStart();
	void onProcess(String data);
	void onFinish(String data);
}
