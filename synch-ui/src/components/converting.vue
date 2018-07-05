/**
 * @file converting.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
<style lang="less">
  .spin-container {
    display: inline-block;
    text-align: center;
    width: 500px;
    height: 200px;
    position: relative;
    border: none;
    .spin-content {
      padding-top: 65px;
    }
  }
</style>
<template>
  <div>
    <Row>
      <Col span="12" offset="6">
        <div class="spin-container">
          <div class="spin-content">
            加载完成，点击下载
            <br>
            <Button type="success">下载</Button>
          </div>
          <Spin size="large" fix v-if="spinShow"></Spin>
        </div>
      </Col>
    </Row>
  </div>

</template>
<script>
export default {
  name: 'converting',
  props: [
    'convertId'
  ],
  data () {
    return {
      spinShow: true
    };
  }
}
</script>
