// pages/login/login.js
var app = getApp();
var host = app.globalData.host;
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  formSubmit(e) {
    wx.request({
      url: host + '/api/user/login',
      method: "POST",
      data: {
        username: e.detail.value.username,
        password: e.detail.value.password
      },
      dataType:"json",
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      success (res) {
        if(res.data.code == 1) {
          // 存储用户信息
          wx.setStorageSync('user', res.data.data)
          console.log(wx.getStorageSync('user'));
          
          wx.showToast({
            title: '登录成功',
          });
          setTimeout(function(){
            wx.switchTab({
              url: '/pages/index/index',
            })
          },1000);
          
        } else {
          wx.showToast({
            title: '登录失败',
            icon: 'none',
            duration: 2000
          });
        }
      }
    })

    
  },

  mini_login: function() {
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId

        console.log(res);
        wx.request({
          url: host + '/api/wx/login',
          method: "POST",
          data: {
            code:res.code
          },
          dataType:"json",
          header: {
            'content-type': 'application/x-www-form-urlencoded' // 默认值
          },
          success: res => {
            console.log(res);
            if(res.data.code == 200) {
              wx.setStorageSync('LOGIN_INFO', res.data.ticket)
            }
            // wx.setStorageSync('token', res.header['token'])
            // this.globalData.token = res.header['token'];
            // if(this.tokenReadyCallback) {
            //   this.tokenReadyCallback(res)
            // }
          },
          fail: res => {
            console.log(res)
          }
        })
      }
    })
  },
  mini_check_login:function() {
    wx.checkSession({
      success () {
        //session_key 未过期，并且在本生命周期一直有效
        wx.showToast({
          title: '处在登录状态',
          icon: "none",
          duration: 2000
        })
      },
      fail () {
        // session_key 已经失效，需要重新执行登录流程
        wx.showToast({
          title: '已经失效重新登录',
          icon: "none",
          duration: 2000
        })
        wx.login() //重新登录
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(wx.getStorageSync('user')) {
      wx.reLaunch({
        url: '/pages/index/index',
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})