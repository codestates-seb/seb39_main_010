import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
blockquote,
body,
dd,
dl,
dt,
fieldset,
figure,
h1,
h2,
h3,
h4,
h5,
h6,
hr,
html,
iframe,
legend,
li,
ol,
p,
pre,
textarea,
ul {
  margin: 0;
  padding: 0;
}
ul, li {
  list-style: none;
}
a {
  text-decoration: none;
}
button {
  cursor: pointer;
}
html,
body {
  height: 100%;
  width: 100%;
  font-family: 'Noto Sans KR', sans-serif;
}
*,
:after,
:before {
  box-sizing: border-box;
}
img,
video {
  height: auto;
  max-width: 100%;
}
iframe {
  border: 0;
}
`;

export default GlobalStyle;
