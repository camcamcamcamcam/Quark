package vazkii.quark.content.automation.block;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.quark.base.block.QuarkPressurePlateBlock;
import vazkii.quark.base.module.QuarkModule;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author WireSegal
 * Created at 9:47 PM on 10/8/19.
 */
public class ObsidianPressurePlateBlock extends QuarkPressurePlateBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public ObsidianPressurePlateBlock(String regname, QuarkModule module, ItemGroup creativeTab, Properties properties) {
        super(null /*Sensitivity is unused*/, regname, module, creativeTab, properties);
        this.setDefaultState(getDefaultState().with(POWERED, false));
    }

    @Override
    protected int computeRedstoneStrength(@Nonnull World worldIn, @Nonnull BlockPos pos) {
        AxisAlignedBB bounds = PRESSURE_AABB.offset(pos);
        List<? extends Entity> entities = worldIn.getEntitiesWithinAABB(PlayerEntity.class, bounds);

        if (!entities.isEmpty()) {
            for(Entity entity : entities) {
                if (!entity.doesEntityNotTriggerPressurePlate()) {
                    return 15;
                }
            }
        }

        return 0;
    }
}
