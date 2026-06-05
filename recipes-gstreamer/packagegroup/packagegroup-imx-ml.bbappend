# Add Kinara Ara-2 NPU nnstreamer sub-plugin only on ara2-capable targets
ML_NNSTREAMER_PKGS:append = "${@bb.utils.contains('MACHINE_FEATURES', 'ara2', ' nnstreamer-ara2', '', d)}"
