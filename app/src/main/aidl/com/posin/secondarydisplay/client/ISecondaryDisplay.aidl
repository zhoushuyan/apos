package com.posin.secondarydisplay.client;

import com.posin.secondarydisplay.client.ISecondaryDisplayCallback;

interface ISecondaryDisplay {
	void send(String msg);
	int execute(String command, ISecondaryDisplayCallback callback);
}
