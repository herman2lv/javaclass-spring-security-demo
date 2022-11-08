export function prepareCsrfRequestHeaders() {
  const csrfHeader = $("meta[name='_csrf_header']").attr("content");
  const csrfToken = $("meta[name='_csrf_token']").attr("content");
  const headers = {};
  headers[csrfHeader] = csrfToken;
  return headers;
}
