import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'
import hljs from 'highlight.js'
import 'highlight.js/styles/atom-one-light.css'
import 'dayjs/locale/zh-cn'
import App from './App.vue'

const app = createApp(App)
app.directive('highlight', function (el) {
  const blocks = el.querySelectorAll('pre code');
  blocks.forEach((block) => {
    hljs.highlightBlock(block);
  });
});
app.use(hljs.vuePlugin) 
app.use(ElementPlus, { locale: zhCn })
app.mount('#app')
