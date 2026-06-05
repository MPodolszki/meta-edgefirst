# Neutron NPU: export buffers as DMA-BUF (EDGEAI-1186)
#
# Replace anon_inode_getfd() with dma_buf_export() in the Neutron
# buffer allocator, enabling zero-copy sharing with V4L2/GStreamer/GPU.

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://0001-staging-neutron-export-buffers-as-dma-buf.patch"

# Ensure the kernel patch really landed; fail early on source drift/conflicts.
do_patch:append() {
	if ! grep -q "neutron_dmabuf_ops" "${S}/drivers/staging/neutron/neutron_buffer.c"; then
		bbfatal "Expected neutron dma-buf patch was not applied to neutron_buffer.c"
	fi
}
