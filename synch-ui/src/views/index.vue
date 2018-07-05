/**
 * @file index.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/2/18
 */
<template>
  <div>
    <Row>
      <Col span="18" offset="3">
      <Card>
        <Steps :current="current">
          <Step title="上传doc文档" content="form表单上传一个待转换的doc文档"></Step>
          <Step title="转换过程中" content="通过socket等待转换结果"></Step>
          <Step title="获取转换结果" content="可下载转换好的pdf文档"></Step>
        </Steps>
        <doc-upload v-if="current == 0" @next="nextStep" :convertId="convertId"></doc-upload>
        <converting v-if="current == 1" @next="nextStep"></converting>
        <convert-result v-if="current == 2"></convert-result>
      </Card>
      </Col>
    </Row>
    <Row>
      <Col span="18" offset="3">
        当前在线人数：{{ people }}
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util'
import docUpload from '@/components/doc-upload'
import converting from '@/components/converting'
import convertResult from '@/components/convert-result'
export default {
  name: 'index',
  components: {
    docUpload, converting, convertResult
  },
  data () {
    return {
      current: 1,
      people: 0,
      ws: null,
      convertId: 0
    };
  },
  created () {
    this.convertId = util.randNumber(5);
  },
  mounted () {
    if ('window' in window) {
      this.initSocket();
    } else {
      this.$Message.error('your browser not support socket !');
    }
  },
  methods: {
    initSocket () {
      let _vue = this;
      this.ws = new WebSocket(util.onlineSocketUrl);
      this.ws.onmessage = (event) => {
        _vue.getMessage(event);
      };
    },
    getMessage (event) {
      this.people = event.data;
    },
    nextStep () {
      this.current++;
    },
    prevStep () {
      this.current--;
    }

  }
}
</script>
