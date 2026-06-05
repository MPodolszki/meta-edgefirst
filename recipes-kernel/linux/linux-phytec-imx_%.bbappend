# Neutron NPU: export buffers as DMA-BUF (EDGEAI-1186)
#
# Replace anon_inode_getfd() with dma_buf_export() in the Neutron
# buffer allocator, enabling zero-copy sharing with V4L2/GStreamer/GPU.

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://0001-staging-neutron-export-buffers-as-dma-buf.patch \
    file://fragment.cfg \
    file://imx95-phyflex-phyvip-2-it6263-hdmi.dtso;outsuffix=freescale \
    file://0001-drm-imx95-ldb-relax-pixel-clock-tolerance.patch \
"

do_configure:prepend() {
    cp ${WORKDIR}/sources-unpack/imx95-phyflex-phyvip-2-it6263-hdmi.dtso \
        ${S}/arch/${ARCH}/boot/dts/freescale/
}