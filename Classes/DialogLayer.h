#ifndef  _DialogLayer_H_
#define  _DialogLayer_H_

#include "cocos2d.h"

USING_NS_CC;
using namespace std;

class DialogLayer: public LayerColor
{
    // 模态对话框菜单
    Menu *m_pMenu;
	bool m_bTouchedMenu;
    
public:
    virtual bool init();
    // 初始化对话框内容
    void initDialog();
    
    void okMenuItemCallback(Ref *pSender);
    void cancelMenuItemCallback(Ref *pSender);

	CREATE_FUNC(DialogLayer);
};

#endif