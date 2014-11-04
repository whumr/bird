
#include "DialogLayer.h"

DialogLayer::DialogLayer(){};

DialogLayer::~DialogLayer(){};

bool DialogLayer::init()
{
    bool bRet = false;    
    do {
        CC_BREAK_IF(!LayerColor::initWithColor(Color4B(0, 0, 0, 125)));        
        this->initDialog();        
        bRet = true;
    } while (0);    
    return bRet;
}

void DialogLayer::initDialog()
{
    Size winSize = Director::getInstance()->getVisibleSize();
    
    auto *label = Label::createWithSystemFont("Are u sure exit?", "", 12);
    label->setPosition(Vec2(winSize.width / 2, winSize.height / 2 + 50));
    this->addChild(label);
    
    auto *okMenuItem = MenuItemFont::create("OK", CC_CALLBACK_1(DialogLayer::okMenuItemCallback,this));
    okMenuItem->setPosition(Vec2(winSize.width / 2 - 100, winSize.height / 2 - 50));
    
    auto *cancelMenuItem = MenuItemFont::create("Cancel", CC_CALLBACK_1(DialogLayer::cancelMenuItemCallback,this));
    cancelMenuItem->setPosition(Vec2(winSize.width / 2 + 100, winSize.height / 2 - 50));
    
    m_pMenu = Menu::create(okMenuItem, cancelMenuItem, NULL);
    m_pMenu->setPosition(Point::ZERO);
    this->addChild(m_pMenu);
}

void DialogLayer::okMenuItemCallback(Ref *pSender)
{
	CCLOG("menu okMenuItemCallback");    
    // 点击确定就退出（拷贝的原有方法）
	Director::getInstance()->end();
    
#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
	exit(0);
#endif
}

void DialogLayer::cancelMenuItemCallback(Ref *pSender)
{	
	CCLOG("menu cancelMenuItemCallback");    
    this->removeFromParentAndCleanup(false);
	Director::getInstance()->resume();
}
