//
//  OptionLayer.cpp
//  parasite-city
//
//  Created by Zhou.Zeyong on 14-2-18.
//
//

#include "OptionLayer.h"



OptionLayer::OptionLayer()
{}

OptionLayer::~OptionLayer()
{}

bool OptionLayer::init()
{
	if(Layer::init()){

		//this->setKeyboardEnabled(true);

		auto dispatcher = Director::getInstance()->getEventDispatcher();
        //触屏事件
		auto listener = EventListenerTouchAllAtOnce::create();
        listener->onTouchesBegan = CC_CALLBACK_2(OptionLayer::onTouchesBegan, this);
        dispatcher->addEventListenerWithSceneGraphPriority(listener, this);
		//按键事件
		auto listenerkeyPad = EventListenerKeyboard::create(); 
		listenerkeyPad->onKeyPressed = CC_CALLBACK_2(OptionLayer::onKeyReleased, this); 
        dispatcher->addEventListenerWithSceneGraphPriority(listenerkeyPad, this); 
		return true;
	}else {
		return false;
	}
}

void OptionLayer::onTouchesBegan(const std::vector<Touch*>& touches, Event *event)
{
	this->delegator->onTouch();
}

void OptionLayer::onKeyReleased(EventKeyboard::KeyCode keycode, Event *event)
{
	//返回
	if (keycode == EventKeyboard::KeyCode::KEY_BACK) {  
		CCLOG("KEY_BACK onKeyReleased..");
		this->delegator->onBack(); 	
	//menu
	} else if (keycode == EventKeyboard::KeyCode::KEY_MENU) {
		MessageBox("menu pressed", "xx");
	} 
}