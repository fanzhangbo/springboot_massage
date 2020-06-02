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
      url: host + '/api/user/changePass',
      method: "POST",
      data: {
        id: wx.getStorageSync('user').id,
        name: wx.getStorageSync('user').username,
        password: e.detail.value.old_password,
        rePassword: e.detail.value.password
      },
      dataType:"json",
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      success (res) {
        if(res.data.code == 1) {
          
          wx.showToast({
            title: '更改成功，重新登录',
            icon: 'none',
            duration: 2000
          });
          setTimeout(function(){
            wx.clearStorageSync()
            wx.reLaunch({
              url: '/pages/login/login',
            });
          },1000);
          
        } else {
          wx.showToast({
            title: '更改失败，请稍后重试',
            icon: 'none',
            duration: 2000
          });
        }
      }
    })

    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(!wx.getStorageSync('user')) {
      wx.reLaunch({
        url: '/pages/login/login',
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